<template>
  <v-app id="mainHome">
    <core-app-bar />
    <!-- <v-app-bar
      app
      clipped-left
      color="blue-grey"
      dark
    >
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      <span class="title ml-3 mr-5">HISC&nbsp;<span class="font-weight-light">{{selectedMenu}}</span></span>
      <div class="flex-grow-1"></div>
      <v-text-field
        hide-details
        label="Search"
        prepend-inner-icon="search"
      ></v-text-field>
      
    </v-app-bar> -->

    <core-drawer />

    <!-- <v-navigation-drawer
      v-model="drawer"
      app
      clipped
      color="grey lighten-4"
    >
      <v-list
        dense
        class="grey lighten-4"
      >
        <template v-for="(item, i) in items">
          <v-row
            v-if="item.heading"
            :key="i"
            align="center"
          >
            <v-col cols="6">
              <v-subheader v-if="item.heading">
                {{ item.heading }}
              </v-subheader>
            </v-col>
            <v-col
              cols="6"
              class="text-right"
            >
              <v-btn
                small
                text
              >edit</v-btn>
            </v-col>
          </v-row>
          <v-divider
            v-else-if="item.divider"
            :key="i"
            dark
            class="my-4"
          ></v-divider>
          <v-list-item
            v-else
            :key="i"
            @click="clickMenu(item)"
          >
            <v-list-item-action>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title class="grey--text">
                {{ item.text }}
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </template>
      </v-list>
    </v-navigation-drawer> -->
    <v-content>
      <v-container
        fluid
        class="grey lighten-4 fill-height"
      >
        <v-row
          justify="center"
          align="center"
        >
          <router-view/>
        </v-row>
      </v-container>
    </v-content>
    

  </v-app>
</template>

<script>
import router from '../router'

  export default {
    components: {
      CoreDrawer: () => import('@/components/Drawer'),
      CoreAppBar: () => import('@/components/AppBar')
    },
    props: {
      source: String,
    },
    data: () => ({
      selectedMenu: "",
      drawer: null,
      items: [
        { icon: 'dashboard', text: 'DashBoard' },
        { icon: 'assessment', text: 'Ward' },
        { icon: 'assessment', text: 'Monitor' },
        { divider: true },
        { heading: 'Labels' },
        { icon: 'add', text: 'Create new label' },
        { divider: true },
        { icon: 'archive', text: 'Archive' },
        { icon: 'delete', text: 'Trash' },
      ],
    }),
    methods: {
      clickMenu(item) {
        this.selectedMenu = item.text
        router.push({ name: item.text })
      }
    }
  }
</script>

<style>
#keep .v-navigation-drawer__border {
  display: none
}
</style>