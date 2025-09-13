<template>
  <div class="prompt-result">
    <pre>
    {{ JSON.stringify(props.promptResults, null, 2) }}
    </pre>

  </div>

</template>

<script setup lang="ts">
import type { PromptResult, Report } from '@/Types/AnalyisRunsTypes';

const props = defineProps({
  promptResults: {
    type: Object as () => PromptResult,
    required: true
  },
});


</script>

<style scoped>
.prompt-result {
  background-color: #f0f0f0;
  padding: 16px;
  border-radius: 4px;
  overflow: scroll;
  margin: 1rem;
}
</style>
