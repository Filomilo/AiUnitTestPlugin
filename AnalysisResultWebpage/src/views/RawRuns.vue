<template>
  <div class="raw-runs">
    <h1>Raw Runs</h1>
    <div class="filters">
      <MultiSelect v-model="selectedProjects" :options="projects" filter placeholder="Select Projects"
        class="w-full md:w-80" />
      <MultiSelect v-model="selectedStrategy" :options="strategies" filter placeholder="Select strategies"
        class="w-full md:w-80" />
      <MultiSelect v-model="selectedModels" :options="models" filter placeholder="Select models"
        class="w-full md:w-80" />
    </div>


    <Tabs value="0">
      <TabList>
        <Tab value="0">Successes ({{ Successes.length }})</Tab>
        <Tab value="1">Fails ({{ Fails.length }})</Tab>
      </TabList>
      <TabPanels>
        <TabPanel value="0">
          <ul>
            <div v-for="(run, index) in Successes" :key="index">
              <RunComponent :run="run" />
            </div>
          </ul>
        </TabPanel>
        <TabPanel value="1">
          <ul>
            <div v-for="(run, index) in Fails" :key="index">
              <RunComponent :run="run" />
            </div>
          </ul>
        </TabPanel>
      </TabPanels>
    </Tabs>


  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed, type Ref, type ComputedRef } from 'vue'
import Tabs from 'primevue/tabs';
import TabList from 'primevue/tablist';
import Tab from 'primevue/tab';
import TabPanels from 'primevue/tabpanels';
import TabPanel from 'primevue/tabpanel';

import type { AnalysisRunsData } from '@/Types/AnalysisRunsTypes';
import type { Run } from '@/Types/AnalysisRunsTypes';
import RunComponent from '@/Components/RunComponent.vue';
import { analysisRunsData } from '@/Data/AnalysisResults';
import { factory } from 'typescript';
import MultiSelect from 'primevue/multiselect';


const Successes = computed(() => filterProjects(analysisRunsData.runs));
const Fails = computed(() => filterProjects(analysisRunsData.fails));


const filterProjects = (runs: (Run)[]): (Run)[] => {
  if (runs === undefined) {
    return [];
  }
  return runs
    .filter(r => selectedProjects.value === undefined || selectedProjects.value.length === 0 || selectedProjects.value.includes(r.project)).sort((a, b) => a.project.localeCompare(b.project))
    .filter(r => selectedStrategy.value === undefined || selectedStrategy.value.length === 0 || selectedStrategy.value.includes(r.strategy.name)).sort((a, b) => a.strategy.name.localeCompare(b.strategy.name))
    .filter(r => selectedModels.value === undefined || selectedModels.value.length === 0 || selectedModels.value.includes(r.llmModel)).sort((a, b) => a.llmModel.localeCompare(b.llmModel));
}

const selectedProjects: Ref<string[] | undefined> = ref();

const projects: ComputedRef<string[]> = computed(() => {
  const projs = new Set<string>();
  console.log("analysisRunsData", analysisRunsData);
  if (analysisRunsData.runs !== undefined) {
    analysisRunsData.runs.forEach(r => projs.add(r.project));
  }
  analysisRunsData.fails.forEach(r => projs.add(r.project));
  return Array.from(projs).sort((a, b) => a.localeCompare(b));
})


const selectedStrategy: Ref<string[] | undefined> = ref();

const strategies: ComputedRef<string[]> = computed(() => {
  const strats = new Set<string>();
  if (analysisRunsData.runs !== undefined) {
    analysisRunsData.runs.forEach(r => strats.add(r.strategy.name));
  }

  analysisRunsData.fails.forEach(r => strats.add(r.strategy.name));
  return Array.from(strats).sort((a: string, b: string) => a.localeCompare(b));
})

const selectedModels: Ref<string[] | undefined> = ref();

const models: ComputedRef<string[]> = computed(() => {
  const mdls = new Set<string>();
  if (analysisRunsData.runs !== undefined) {
    analysisRunsData.runs.forEach(r => mdls.add(r.llmModel));
  }
  analysisRunsData.fails.forEach(r => mdls.add(r.llmModel));
  return Array.from(mdls).sort((a: string, b: string) => a.localeCompare(b));
})

</script>

<style scoped>
.raw-runs {
  padding: 1rem;
}
</style>
