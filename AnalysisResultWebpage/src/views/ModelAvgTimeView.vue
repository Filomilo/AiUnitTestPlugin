<template>
  <div class="model-avg-time-view">
    <h1>Model Average Time</h1>
    <table v-if="modelTimes.length">
      <thead>
        <tr>
          <th>Model Name</th>
          <th>Average Time (ms)</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="model in modelTimes" :key="model.name">
          <td>{{ model.name }}</td>
          <td>{{ msToDurationString(model.avgTime) }}</td>
        </tr>
      </tbody>
    </table>
    <p v-else>No data available.</p>
  </div>
</template>

<script setup lang="ts">
import { LLMCache } from '@/Data/PromptCache';
import { durationStringToMs, msToDurationString } from '@/Tools/StringTools';
import type { PromptResult } from '@/Types/AnalyisRunsTypes';
import { ref, onMounted } from 'vue'
import type { Ref } from 'vue';
const modelTimes: Ref<{ name: string, avgTime: number }[]> = ref([])

onMounted(() => {
  var counter: { [key: string]: { totalTime: number; count: number } } = {};
  LLMCache.forEach((prompt: PromptResult) => {
    if (counter[prompt.modelName]) {
      if (prompt.modelName === "Ollama-moondream") {
        console.log("Found Moondream prompt with time: " + prompt.generationTime + " in ms: " + durationStringToMs(prompt.generationTime));
      }
      counter[prompt.modelName].totalTime += durationStringToMs(prompt.generationTime);
      counter[prompt.modelName].count += 1;
    } else {
      counter[prompt.modelName] = { totalTime: durationStringToMs(prompt.generationTime), count: 1 };
    }
  })

  console.log((Object.keys(counter)));
  for (let key in Object.keys(counter)) {

    const model = counter[Object.keys(counter)[key]];
    // console.log(Object.keys(counter)[key] + "::\n" + JSON.stringify(model, null, 2));
    // counter[key].totalTime = Math.round(model.totalTime / model.count);
    modelTimes.value.push({ name: Object.keys(counter)[key], avgTime: Math.round(model.totalTime / model.count) });
  }
  modelTimes.value.sort((a, b) => a.avgTime - b.avgTime);

});
</script>

<style scoped>
.model-avg-time-view {
  max-width: 600px;
  margin: 2rem auto;
  padding: 1rem;
  background: #fafafa;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 1rem;
}

th,
td {
  padding: 0.5rem 1rem;
  border-bottom: 1px solid #ddd;
  text-align: left;
}

th {
  background: #f0f0f0;
}
</style>
