<template>
  <div class="runContainer">

    <div class="topbar">
      <div>
        <h3 class="project-name">
          {{ run.project }}
        </h3>

        <div>
          <StrategyComponent :strategy="run.strategy" />
        </div>
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
    <div v-if="failureReasaon !== undefined" style="color: red; font-weight: bold; margin-top: 1rem;">
      Failure Reason: {{ failureReasaon.message }}

    </div>
    <Accordion :activeIndex="0">
      <AccordionTab header="Report" v-if="report !== undefined">
        <Report :report="report" />
      </AccordionTab>

      <AccordionTab header="Execution Logs" v-if="logs !== undefined && logs.length > 0">
        <pre>{{ logs.join("\n") }}</pre>
      </AccordionTab>

      <AccordionTab header="Device specification" v-if="deviceSpec !== undefined">
        <DeviceSpecification :report="deviceSpec" />

      </AccordionTab>


      <AccordionTab header="Generated files" v-if="generatedFiles !== undefined && generatedFiles.length > 0">
        <GenratedFiles :generated-files="generatedFiles" />

      </AccordionTab>

      <AccordionTab header="Prompt results" v-if="promptResults !== undefined">

        <PromptResponsesList :responses="promptResults" />
      </AccordionTab>


      <AccordionTab :header="'Warnings (' + warnings.length + ')'" v-if="warnings !== undefined && warnings.length > 0">
        <div v-for="warning in warnings">
          <Warning :warning="warning" />
        </div>
      </AccordionTab>


    </Accordion>

  </div>

</template>

<script setup lang="ts">
import { defineProps } from "vue";
import { formatDuration, isoToDate } from "@/Tools/StringTools";
import type { Run, Report as ReportType, DeviceSpecification as DeviceSpecificationType, GeneratedFile, PromptResult, Warning as WarningType, FailureReason } from "@/Types/AnalysisRunsTypes";
import Accordion from 'primevue/accordion';
import AccordionTab from 'primevue/accordiontab';
import Report from '@/Components/Report.vue';
import Warning from '@/Components/Warning.vue';
import DeviceSpecification from '@/Components/DeviceSpecification.vue';
import GenratedFiles from "@/Components/GeneratedFiles.vue";
import PrompResult from "./PrompResult.vue";
import PromptResponsesList from "./PromptResponsesList.vue";
const props = defineProps({
  run: {
    type: Object as () => Run,
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
const warnings: WarningType[] | undefined = (props.run as Run).warnings
const failureReasaon: FailureReason | undefined = (props.run as Run).failureReason

console.log(JSON.stringify(deviceSpec));
</script>
<style scoped>
.runContainer {
  border: 1px solid var(--surface-d);
  padding: 1rem;
  margin-bottom: 1rem;
  border-radius: 8px;
  background-color: var(--surface-ground);
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
