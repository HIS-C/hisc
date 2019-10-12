<template>
  <v-container
    fill-height
    fluid
    grid-list-xl
  >
    <v-row>
      <v-col class="d-flex" cols="3">
        <v-select
          :items="infectCodes"
          @input="selInfectCode"
          label="Solo field"
          solo
        ></v-select>
      </v-col>
      <!-- <v-col class="d-flex" cols="3">
        <v-select
          :items="wards"
          @input="selWard"
          label="Solo field"
          solo
        ></v-select>
      </v-col> -->
      <v-col cols="12">
        <v-divider class="mx-3 mb-3" />
      </v-col>
      <v-col cols="12">
      <v-card
        title="test"
        color="blue-grey lighten-3"
      >
        <v-card-title>감염자</v-card-title>
      </v-card>
        <v-data-table
          dense 
          :headers="headers"
          :items="filteredItems"
          :items-per-page="10"
        />
      </v-col>
      <v-col cols="12">
      <v-card
        title="test"
        color="blue-grey lighten-3"
      >
        <v-card-title>후보군</v-card-title>
      </v-card>
        <v-data-table
          dense 
          :headers="headers"
          :items="filteredItems"
          :items-per-page="5"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  import esCli from '../api/esCli';

  export default {
    data: () => ({
      headers: [
        {
          sortable: false,
          text: '등록번호',
          value: '_source.patientId'
        },
        {
          sortable: false,
          text: '입원일자',
          value: '_source.admissionDate'
        },
        {
          sortable: false,
          text: '진료과',
          value: '_source.deptCode'
        },
        {
          sortable: false,
          text: '주치의',
          value: '_source.doctorName'
        },
        {
          sortable: false,
          text: '감염구분',
          value: '_source.infectCode'
        },
      ],
      filter: {
        ward: "",
        infectCode: ""
      },
      items: [],
      filteredItems: [],
      wards: [],
      infectCodes: ['','CRE','VRE','MRSA','MRAB'],
    }),
    methods: {
      selWard: function(arg) {
        debugger;
        this.filter.ward = arg;
        this.setFiler();
      },
      selInfectCode: function(arg) {
        debugger;
        this.filter.infectCode = arg;
        this.setFiler();
      },
      setFiler: function () {
        debugger;
        /*if(this.filter.ward != "") {
          this.filteredItems = this.items.filter(item => 
            item._source.ward == this.filter.ward
          )
        }*/
        if(this.filter.infectCode != "") {
          this.filteredItems = this.items.filter(item => 
            item._source.infectCode == this.filter.infectCode
          )
        }else{
          this.filteredItems = this.items;
        }
      }

    },
    mounted() {
      esCli.getExams().then(res => {
        debugger;
        this.items = res;
        this.filteredItems = res;
        console.log(res);
      });
      
      esCli.getWards().then(res => {
        res.forEach(element => {
          this.wards.push(element._source.wardName) ;
        });
        console.log(this.wards);
      });
    },
  };
</script>

<style scoped>

</style>