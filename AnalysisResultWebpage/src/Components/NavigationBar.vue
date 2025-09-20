<template>
  <nav class="navbar">
    <router-link v-for="link in links" :key="link.path" :to="link.path" class="nav-link" active-class="active">
      {{ link.name }}
    </router-link>
  </nav>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const links = ref([
  { name: 'Runs', path: '/RawRuns' },
  { name: 'Prompts', path: '/RawPrompts' },
  { name: 'Prompt Tester', path: '/PromptTester' },
])
</script>

<style scoped>
.navbar {
  display: flex;
  gap: 1rem;
  background: #222;
  padding: 1rem;
}

.nav-link {
  color: #fff;
  text-decoration: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: background 0.2s;
}

.nav-link.active,
.nav-link:hover {
  background: #444;
}
</style>
