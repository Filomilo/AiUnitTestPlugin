<template>
  <div class="report">
    Coverage: {{ JSON.stringify(props.report.CoveragePercent) }}%
  </div>
</template>

<script setup lang="ts">
import type { Report } from '@/Types/AnalyisRunsTypes';

const props = defineProps({
  report: {
    type: Object as () => Report,
    required: true
  },
});


</script>

<style scoped>
.report {
  background-color: --surface-b;
  padding: 16px;
  border-radius: 4px;
}
</style>
