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
      max-width="600"
    >
      <v-card-title>
        <div>{{item._source.wardName}}</div>
      </v-card-title>
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
      items : [
        {
          ward : '153W'
        },
        {
          ward : '154W'
        },
        {
          ward : '155W'
        },
        {
          ward : '157W'
        },
        {
          ward : '161W'
        },
        {
          ward : '162W'
        },
        {
          ward : '163W'
        },
        {
          ward : '164W'
        },
        {
          ward : '165W'
        },
        {
          ward : '166W'
        },
        {
          ward : '171W'
        },
        {
          ward : '172W'
        },
        {
          ward : '173W'
        },
        {
          ward : '174W'
        },
        {
          ward : '175W'
        },
        {
          ward : '176W'
        },
        {
          ward : '178W'
        },
        {
          ward : '181W'
        },
        {
          ward : '182W'
        },
        {
          ward : '183W'
        },
        {
          ward : '185W'
        },
        {
          ward : '186W'
        },
        {
          ward : '188W'
        },
        {
          ward : '190W'
        },
        {
          ward : '1DER'
        },
        {
          ward : '1HRPU'
        },
        {
          ward : '1NNR'
        },
        {
          ward : 'ER'
        },
        {
          ward : 'ICU-C'
        },
        {
          ward : 'ICU-E'
        },
        {
          ward : 'ICU-M'
        },
        {
          ward : 'ICU-N'
        },
        {
          ward : 'ICU-S'
        }
      ]
    }),
    mounted() {
      esCli.getWards().then(res => {
        this.wards = res
      });
    }
  }
</script>