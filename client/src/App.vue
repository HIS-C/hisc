<template>
  <v-app>
    <v-container>
      <v-layout text-center wrap>
        <router-link to="/">Home</router-link> |
        <router-link to="/about">About</router-link>
      </v-layout>
    </v-container>
    <router-view />
  </v-app>
</template>

<script>
  export default {
    name: 'App',
    data: () => ({
      //
    }),
  }
</script>
