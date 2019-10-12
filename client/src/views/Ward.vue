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
          <span class="subheading mr-1">12</span>
          <span class="mr-1"> / 총감염자수</span>
          <span class="subheading mr-1">0</span>
        </v-row>
      <v-card-text class="body-1">
        <v-row
          >
          <v-col cols="6"><span class="green mr-1">MRSA : {{test.MRSA}}</span></v-col>
          <v-col cols="6"><span class="light-blue mr-1">VRSA : {{test.VRSA}}</span></v-col> 
        </v-row>
        <v-row
          >
          <v-col cols="6"><span class="light-green mr-1">MRAB : {{test.MRAB}}</span></v-col> 
          <v-col cols="6"><span class="blue mr-1">MRPA : {{test.MRPA}}</span> </v-col>
        </v-row>
        <v-row
          >
          <v-col cols="6"><span class="red mr-1">CRE : {{test.CRE}}</span> </v-col> 
          <v-col cols="6"><span class="purple mr-1">VRE : {{test.VRE}}</span> </v-col>
        </v-row>
      </v-card-text>
    </v-card>
          </v-col>
        </v-row>
  </template>
  </v-data-iterator>
  <v-navigation-drawer
      v-model="drawerRight"
      app
      clipped
      right
  ></v-navigation-drawer>
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
        
      }
    }),
    mounted() {
      esCli.getWards().then(res => {
        this.wards = res
      });
    }
  }
</script>