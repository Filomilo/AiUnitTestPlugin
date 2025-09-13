<template>
  <div class="files_container">
    <!-- <Tree v-model:selectionKeys="selectedKey" :value="treeNodes" selectionMode="single" class="w-full md:w-[30rem] ">
    </Tree> -->
    <pre>{{ JSON.stringify(props.generatedFiles, null, 2) }}</pre>
  </div>

</template>

<script setup lang="ts">

import Tree, { type TreeSelectionKeys } from 'primevue/tree'
import type { GeneratedFile } from '@/Types/AnalyisRunsTypes';
import { ref, onMounted, type ComputedRef, computed, type Ref } from 'vue';
import type { TreeNode } from 'primevue/treenode';
import { mapToTreeNode } from '@/Tools/Mapper';
const selectedKey: Ref<TreeSelectionKeys | undefined> = ref(undefined);


const props = defineProps({
  generatedFiles: {
    type: Array as () => GeneratedFile[],
    required: true
  },
});

const treeNodes: ComputedRef<TreeNode[]> = computed(() => {
  return props.generatedFiles.map(f => mapToTreeNode(f, f.name));
})


</script>


<style lang="css" scoped>
.leftPanel {
  width: 30%;
  border-right: 1px solid #ccc;
  padding-right: 1rem;
}

.files_container {
  padding: 1rem;
  border-left: 1px solid #ccc;
  overflow: scroll;

}
</style>
