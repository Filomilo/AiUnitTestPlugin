<template>
  <div class="raw-prompt-cache">
    <h1>Raw Prompt Cache</h1>
    <PromptResponsesList :responses="prompts" />
  </div>
</template>

<script setup lang="ts">
import PromptResponsesList from '@/Components/PromptResponsesList.vue';
import { LLMCache } from '@/Data/PromptCache'
import type { PromptResult } from '@/Types/AnalyisRunsTypes';
import { ref } from 'vue'

const prompts: PromptResult[] = LLMCache as unknown as PromptResult[];
</script>

<style scoped>
.raw-prompt-cache {
  padding: 2rem;
  font-family: Arial, sans-serif;
}
</style>
