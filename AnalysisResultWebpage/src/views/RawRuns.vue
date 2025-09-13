<template>
  <div class="raw-runs">
    <h1>Raw Runs</h1>
    <Tabs value="0">
      <TabList>
        <Tab value="0">Successes ({{ analysisRunsData.runs.length }})</Tab>
        <Tab value="1">Fails ({{ analysisRunsData.fails.length }})</Tab>
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
import { ref, onMounted, computed } from 'vue'
import Tabs from 'primevue/tabs';
import TabList from 'primevue/tablist';
import Tab from 'primevue/tab';
import TabPanels from 'primevue/tabpanels';
import TabPanel from 'primevue/tabpanel';

import type { AnalysisRunsData } from '@/Types/AnalyisRunsTypes';
import type { Run } from '@/Types/AnalyisRunsTypes';
import RunComponent from '@/Components/RunComponent.vue';
import { analysisRunsData } from '@/Data/AnalysisResults';
import { factory } from 'typescript';


const Successes = computed(() => analysisRunsData.runs.sort((a, b) => a.project.localeCompare(b.project)));
const Fails = computed(() => analysisRunsData.fails.sort((a, b) => a.project.localeCompare(b.project)));

</script>

<style scoped>
.raw-runs {
  padding: 1rem;
}
</style>
