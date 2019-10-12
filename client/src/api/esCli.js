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
  /**
   * 병동목록조회
   */
  getWards() {
    return this.search({
      index: 'ward'
    });
  },
  /**
   * 검사결과목록조회
   */
  getExams() {
    return this.search({
      index: 'exam'
    });
  },
};