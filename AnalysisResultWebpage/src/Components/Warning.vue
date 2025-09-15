<template>
  <div class="warning">
    <h3>
      {{ props.warning.type }}
    </h3>
    <pre class="codeContent">
    {{ props.warning.message }}
    </pre>

  </div>

</template>

<script setup lang="ts">
import type { PromptResult, Report, Warning } from '@/Types/AnalyisRunsTypes';

const props = defineProps({
  warning: {
    type: Object as () => Warning,
    required: true
  },
});


</script>

<style scoped>
.warning {
  background-color: #e9be4760;
  padding: 16px;
  border-radius: 4px;
  overflow: auto;
  margin: 1rem;
  border-radius: 1rem;
}

.codeContent {
  white-space: pre-wrap;
  word-wrap: break-word;
}
</style>
