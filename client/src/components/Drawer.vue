<template>
  <v-navigation-drawer
      id="app-drawer"
      v-model="inputValue"
      :src="image"
      app
      color="grey darken-2"
      dark
      floating
      mobile-break-point="991"
      persistent
      width="210"
  >
    <template v-slot:img="attrs">
      <v-img
          v-bind="attrs"
          gradient="to top, rgba(0, 0, 0, .7), rgba(0, 0, 0, .7)"
      />
    </template>

    <v-list-item two-line>
      <v-list-item-avatar color="white">
        <v-img
            src="https://cdn.vuetifyjs.com/images/logos/v.png"
            height="34"
            contain
        />
      </v-list-item-avatar>

      <v-list-item-title class="title">
        HISC종합병원
      </v-list-item-title>
    </v-list-item>

    <v-divider class="mx-3 mb-3" />

    <v-list nav>
      <!-- Bug in Vuetify for first child of v-list not receiving proper border-radius -->
      <div />

      <v-list-item
          v-for="(link, i) in links"
          :key="i"
          :to="link.to"
          active-class="light-green accent-3"
      >
        <v-list-item-action>
          <v-icon>{{ link.icon }}</v-icon>
        </v-list-item-action>

        <v-list-item-title v-text="link.text" />
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
  export default {
    props: {
      opened: {
        type: Boolean,
        default: false,
      },
    },
    data: () => ({
      links:
        [
          {
            to: '/',
            icon: 'mdi-view-dashboard',
            text: 'Dashboard',
          },
          {
            to: '/ward',
            icon: 'mdi-clipboard-outline',
            text: 'Ward',
          },
          {
            to: '/monitor',
            icon: 'assessment',
            text: 'Monitor',
          },
        ],
    }),
  };
</script>
