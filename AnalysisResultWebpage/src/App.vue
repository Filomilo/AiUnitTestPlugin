<script setup lang="ts">

import { RouterLink, RouterView } from 'vue-router'

</script>

<template>
  <h1>Hello App!</h1>
  <p><strong>Current route path:</strong> {{ $route.fullPath }}</p>
  <nav>
    <RouterLink to="/RawRuns">Go to Home</RouterLink>
    <RouterLink to="/RawPrompts">Go to About</RouterLink>
  </nav>
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
