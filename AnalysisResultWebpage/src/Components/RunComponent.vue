<template>
  <div class="runContainer">

    <div class="topbar">
      <div>
        <h3 class="project-name">
          {{ run.project }}
        </h3>
        <h4>
          {{ run.strategy }}
        </h4>
        <h4>
          {{ run.llmModel }}
        </h4>
      </div>
      <div class="rightSideContainer">
        {{ date.toLocaleString() }}
        <div v-if="duration !== undefined">
          duration:
          {{ duration }}
        </div>
      </div>



    </div>
    <Accordion :activeIndex="0">
      <AccordionTab header="Report" v-if="report !== undefined">
        <Report :report="report" />
      </AccordionTab>

      <AccordionTab header="Execution Logs" v-if="logs !== undefined">
        <pre>{{ logs.join("\n") }}</pre>
      </AccordionTab>

      <AccordionTab header="Device specification" v-if="deviceSpec !== undefined">
        <DeviceSpecification :report="deviceSpec" />

      </AccordionTab>


      <AccordionTab header="Generated files" v-if="generatedFiles !== undefined">
        <GenratedFiles :generated-files="generatedFiles" />

      </AccordionTab>

      <AccordionTab header="Prompt results" v-if="generatedFiles !== undefined">
        <div v-for="prompResult in promptResults">
          <PrompResult :promptResults="promptResults" />

        </div>

      </AccordionTab>

    </Accordion>

  </div>

</template>

<script setup lang="ts">
import { defineProps } from "vue";
import { formatDuration, isoToDate } from "@/Tools/StringTools";
import type { Run, Fail, Report as ReportType, DeviceSpecification as DeviceSpecificationType, GeneratedFile, PromptResult } from "@/Types/AnalyisRunsTypes";
import Accordion from 'primevue/accordion';
import AccordionTab from 'primevue/accordiontab';
import Report from '@/Components/Report.vue';

import DeviceSpecification from '@/Components/DeviceSpecification.vue';
import GenratedFiles from "./GenratedFiles.vue";
import PrompResult from "./PrompResult.vue";
const props = defineProps({
  run: {
    type: Object as () => Run | Fail,
    required: true,
  },
});

const date: Date = isoToDate(props.run.time);

const report: ReportType | undefined = (props.run as Run).report;

const duration: String | undefined = (props.run as Run).duration == undefined ? undefined : formatDuration((props.run as Run).duration);

const logs: String[] | undefined = (props.run as Run).executionLogs

const deviceSpec: DeviceSpecificationType | undefined = (props.run as Run).deviceSpecification

const generatedFiles: GeneratedFile[] | undefined = (props.run as Run).generatedFiles
const promptResults: PromptResult[] | undefined = (props.run as Run).promptResults


console.log(JSON.stringify(deviceSpec));
</script>
<style scoped>
.runContainer {
  border: 1px solid #ccc;
  padding: 1rem;
  margin-bottom: 1rem;
  border-radius: 8px;
  background-color: #f9f9f9;
}

.project-name {
  font-weight: bold;
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
}

.topbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.rightSideContainer {
  text-align: right;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
}
</style>
