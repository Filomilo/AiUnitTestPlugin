<script setup lang="ts">

import { RouterLink, RouterView } from 'vue-router'
import NavigationBar from './Components/NavigationBar.vue';

</script>

<template>
  <NavigationBar />
  <main>
    <RouterView />
  </main>
</template>

<style>
#app {
  font-family: "Gill Sans", sans-serif !important;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

h3 {
  font-family: "Gill Sans", sans-serif !important;
  margin: 0;
  padding: 0;
}
</style>
