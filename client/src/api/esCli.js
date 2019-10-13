import esConf from '../config/es.conf.js';

const elasticsearch = require('elasticsearch');

const esCli = new elasticsearch.Client({
  host: esConf.host,
  cors: true,
  keepAlive: false,
  apiVersion: '7.3',
  // log: 'trace'
});

export default {
  search(reqParam) {
    return new Promise((resolve, reject) => {
      esCli.search(reqParam, (err, resp, status) => {
        if (status == '200') {
          resolve(resp);
        } else {
          reject(err);
        }
      });
    }).then((resp) => {
      return resp.hits.hits;
    });
  },
  getPersons(params) {
    return this.search({
      index: 'patient',
      body: {
        "query": {
          "terms": {
            "patientId": params
          }
        }
      }
    })
  },
  /**
   * 병동목록조회
   */
  getWards() {
    return this.search({
      index: 'ward',
    });
  },
  getAdmissions() {
    return this.search({
      index: 'admission',
      body: {
        size: 1000,
        query: {
          'term': {
            'dischargeDate.keyword': {
              'value': 'NULL',
            },
          },
        },
      },
    });
  },
  /**
   * 후보자 transgers 조회
   */
  getTransfers() {
    return new Promise((resolve) => {
      this.getAdmissions().then(res => {
        let persons = res.map(it => it._source.patientId)
        let sorted = res.map(it => it.orderDate).slice(0).sort();

          this.search({
            index: 'transfer',
            body: {
              'query': {
                'terms': {
                  'patientId': persons,
                },
              },
            },
          }).then(res => {
            let transfers = res.map(it => it._source.wardId);
            resolve(this.search({
              index: 'transfer',
              body: {
                "query": {
                  "bool": {
                    "must": [
                      {
                        "terms": {
                          "wardId": transfers
                        }
                      },
                      {
                        "terms": {
                          "patientId": persons
                        }
                      },
                      {
                        "range": {
                          "transferStrDate": {
                            'gte': sorted[0],
                            'lte': sorted[sorted.length - 1],
                          }
                        }
                      }
                    ]
                  }
                },
              },
            }))
          }
        );
      });
    });
  },
  /**
   * 검사결과목록조회
   */
  getExams(condition) {
    let query = {
      index: 'exam',
    };

    if (condition) {
      query.body = condition;
    }

    return this.search(query);
  }
  ,
  /**
   * 입원환자들의 검사결과목록조회
   */
  getCurrentExams() {
    return new Promise((resolve) => {
      this.getAdmissions().then(res => {
        let sorted = res.map(it => it.orderDate).slice(0).sort();
        resolve(
          this.getExams(
            {
              size: 1000,
              'query': {
                'bool': {
                  'must': [
                    {
                      'terms': {
                        'infectCode': [
                          'CRE', 'VRE', 'MRSA', 'MRAB',
                        ],
                      },
                    },
                    {
                      'terms': {
                        'patientId': res.map(it => it._source.patientId),
                      },
                    },
                    {
                      'range': {
                        'orderExecDate': {
                          'gte': sorted[0],
                          'lte': sorted[sorted.length - 1],
                        },
                      },
                    },
                  ],
                },
              },
            },
          ),
        );
      });
    });
  }
};