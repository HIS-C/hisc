<template>
  <v-container fluid>
  <v-data-iterator
    :items="wards"
    :items-per-page.sync="itemsPerPage"
    hide-default-footer
  >
  <template v-slot:default="props">
        <v-row>
          <v-col
            v-for="item in props.items"
            :key="item._source.wordId"
            cols="12"
            sm="6"
            md="4"
            lg="3"
          >
    <v-card
      class="mx-auto"
      color="#26c6da"
      dark
      max-width="400"
    >
      <v-card-title>
        <span class="title font-weight-light">{{item._source.wardName}}</span>
      </v-card-title>
        <v-row
            align="center"
            justify="center"
          >
          <span class="mr-1">총환자수</span>
          <span class="subheading mr-1">{{wardPersons[item._source.wardName].length}}</span>
          <span class="mr-1"> / 총감염자수</span>
          <span class="subheading mr-1">0</span>
        </v-row>
      <v-card-text class="body-1">
        <v-row
          >
          <v-col cols="6"><span class="green mr-1">CRE : {{getTestCount(item._source.wardName, 'CRE')}}</span></v-col>
          <v-col cols="6"><span class="blue mr-1">VRE : {{getTestCount(item._source.wardName, 'VRE')}}</span></v-col>
        </v-row>
        <v-row
          >
          <v-col cols="6"><span class="red mr-1">MRSA : {{getTestCount(item._source.wardName, 'MRSA')}}</span></v-col>
          <v-col cols="6"><span class="purple mr-1">MRAB : {{getTestCount(item._source.wardName, 'MRAB')}}</span> </v-col>
        </v-row>
        <!--<v-row
          >
          <v-col cols="6"><span class="red mr-1">CRE : {{test.CRE}}</span> </v-col> 
          <v-col cols="6"><span class="purple mr-1">VRE : {{test.VRE}}</span> </v-col>
        </v-row>-->
      </v-card-text>
    </v-card>
          </v-col>
        </v-row>
  </template>
  </v-data-iterator>
  </v-container>
</template>

<script>
  import esCli from '../api/esCli';

  export default {
    data: () => ({
      show: false,
      wards : [],
      test : {
        MRSA : 0,
        VRSA : 0,
        MRAB : 1,
        MRPA : 0,
        VRE : 2,
        CRE : 0,
      },
      personWards: {},
      wardPersons: {},
      exams: {},
      //'CRE', 'VRE', 'MRSA', 'MRAB',
    }),
    methods: {
      getTestCount: function(wardName, testName) {
        console.log('exams!!!!!!!!!!!')
        console.log(this.exams)
        debugger
        return (this.exams[wardName] && this.exams[wardName][testName]) ?
          this.exams[wardName][testName] : 0
      }
    },
    mounted() {
      esCli.getAdmissionsWithVo().then(res => {
        esCli.getWardPersons(res.persons).then(res => {
          res.forEach(it => {
            this.personWards[it._source.patientId] = it._source.wardId
            if(this.wardPersons[it._source.wardId]) {
              this.wardPersons[it._source.wardId].push(it._source.patientId)
            }
            else {
              this.wardPersons[it._source.wardId] = []
              this.wardPersons[it._source.wardId].push(it._source.patientId)
            }
          })
          esCli.getCurrentExams().then(res => {
            res.forEach(it => {
              if(this.exams[this.personWards[it._source.patientId]] && this.exams[this.personWards[it._source.patientId]][it._source.infectCode]) {
                this.exams[this.personWards[it._source.patientId]][it._source.infectCode] = this.exams[this.personWards[it._source.patientId]][it._source.infectCode] + 1
              } else if(this.personWards[it._source.patientId]) {
                if(this.exams[this.personWards[it._source.patientId]]) {
                  this.exams[this.personWards[it._source.patientId]][it._source.infectCode] = 1
                } else {
                  this.exams[this.personWards[it._source.patientId]] = {}
                  this.exams[this.personWards[it._source.patientId]][it._source.infectCode] = 1
                }
              }
            })
            esCli.getWards().then(res => {
              res.forEach(it => {
                if(!this.wardPersons[it._source.wardName]) {
                  this.wardPersons[it._source.wardName] = []
                }
              })
              this.wards = res
            });
          })
        })
      })
    }
  }
</script>