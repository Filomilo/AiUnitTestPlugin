<template>
  <div>

    <div v-for="prompResult in responses">
      <PrompResult :promptResults="prompResult" />

    </div>
  </div>
</template>

<script setup>
import { defineProps } from 'vue'
import PrompResult from './PrompResult.vue'
const props = defineProps({
  responses: {
    type: Array,
    required: true,
    default: () => []
  }
})
</script>
