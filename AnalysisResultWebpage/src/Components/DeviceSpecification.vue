<template>
  <div class="device-specification">
    <pre>{{ JSON.stringify(props.report, null, 2) }}</pre>

  </div>
</template>

<script setup lang="ts">
import type { DeviceSpecification } from '@/Types/AnalyisRunsTypes';

const props = defineProps({
  report: {
    type: Object as () => DeviceSpecification,
    required: true
  },
});

</script>

<style scoped>
.device-specification {
  border: 1px solid #ccc;
  padding: 1rem;
  border-radius: 6px;
  background: #fafafa;
}

.device-specification h2 {
  margin-bottom: 0.5rem;
}

.device-specification ul {
  list-style: none;
  padding: 0;
}

.device-specification li {
  margin-bottom: 0.25rem;
}
</style>
