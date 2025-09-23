<template>
  <div class="strategy-component">
    <h2>{{ props.strategy.name }}</h2>
    <p>{{ props.strategy.description }}</p>
    <div class="tags">
      <span v-for="tag in props.strategy.tags" :key="tag" class="tag">{{ tag }}</span>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { strategy } from '@/Types/AnalysisRunsTypes';


const props = defineProps({
  strategy: {
    type: Object as () => strategy,
    required: true
  },
});
</script>

<style scoped>
.strategy-component {
  border: 1px solid #ddd;
  padding: 16px;
  border-radius: 8px;
  max-width: 400px;
}

.tags {
  margin-top: 8px;
}

.tag {
  display: inline-block;
  background: #eef;
  color: #336;
  padding: 2px 8px;
  margin-right: 4px;
  border-radius: 4px;
  font-size: 0.9em;
}
</style>
