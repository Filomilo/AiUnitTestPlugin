<template>
  <div class="prompt-result">
    model: {{ props.promptResults.modelName }} <br />
    time: {{ props.promptResults.generationTime }} <br />
    <h3>
      Prompt:
    </h3>
    <pre>
    {{ props.promptResults.prompt }}
    </pre>
    <h3>
      Response:
    </h3>
    <pre> {{ response }} </pre>
    <DeviceSpecification v-if="props.promptResults.deviceSpecification"
      :report="props.promptResults.deviceSpecification" />
  </div>

</template>

<script setup lang="ts">
import type { PromptResult, Report } from '@/Types/AnalysisRunsTypes';
import DeviceSpecification from './DeviceSpecification.vue';
import { computed } from 'vue';
import { FormatEscapeSequences } from '@/Tools/StringTools';

const props = defineProps({
  promptResults: {
    type: Object as () => PromptResult,
    required: true
  },
});


const response = computed(() => {
  return FormatEscapeSequences(props.promptResults.response)
})

</script>

<style scoped>
.prompt-result {
  background-color: #f0f0f0;
  padding: 16px;
  border-radius: 4px;
  overflow: scroll;
  margin: 1rem;
}

.prompt-result pre {
  word-wrap: break-word;
  white-space: pre-wrap;
}
</style>
