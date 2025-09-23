<template>
  <div>

    <div v-for="prompResult in responses">
      <PrompResult :promptResults="prompResult" />

    </div>
  </div>
</template>

<script setup lang="ts">
import { defineProps } from 'vue'
import PrompResult from './PrompResult.vue'
import type { PromptResult } from '@/Types/AnalysisRunsTypes';
const props = defineProps({
  responses: {
    type: Array as () => PromptResult[],
    required: true,
    default: () => []
  }
})
</script>
