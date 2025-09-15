<template>
  <div class="files_container">
    <div class="leftPanel">
      <tree :value="treeNodes" selection-mode="single" :selection-keys="selectedKey"
        @update:selection-keys="(e) => selectedKey = e" />
    </div>
    <div class="files_content">
      <pre v-if="selectedContent !== undefined">{{ selectedContent }}</pre>
    </div>
    <!-- {{ JSON.stringify(treeNodes, null, 2) }} -->
  </div>

</template>

<script setup lang="ts">

import Tree, { type TreeSelectionKeys } from 'primevue/tree'
import type { GeneratedFile } from '@/Types/AnalyisRunsTypes';
import { ref, onMounted, type ComputedRef, computed, type Ref } from 'vue';
import type { TreeNode } from 'primevue/treenode';
import { mapToTreeNode } from '@/Tools/Mapper';
const selectedKey: Ref<any> = ref(undefined);


const props = defineProps({
  generatedFiles: {
    type: Array as () => GeneratedFile[],
    required: true
  },
});

const treeNodes: ComputedRef<TreeNode[]> = computed(() => {
  return props.generatedFiles.map(f => mapToTreeNode(f, f.name));
})

const selectedContent: ComputedRef<String | undefined> = computed(() => {
  if (selectedKey.value === undefined || Object.keys(selectedKey.value)[0] === undefined) {
    return undefined;
  }
  console.log(JSON.stringify(selectedKey.value));
  // console.log("path: " + JSON.stringify(Object.keys(selectedKey.value)[0]));
  const path = (Object.keys(selectedKey.value)[0] as string).split('/');
  // console.log("path: " + JSON.stringify(path));
  let current: GeneratedFile | undefined;
  for (const part of path) {
    if (current === undefined) {
      current = props.generatedFiles.find(f => f.name === part);
    } else {
      if (current.children === undefined) {
        return undefined;
      }
      current = current.children.find(f => f.name === part);
    }
    if (current === undefined) {
      return undefined;
    }
  }
  return current.content;
})

</script>


<style lang="css" scoped>
.leftPanel {
  width: 30%;
  border-right: 1px solid #ccc;
  padding-right: 1rem;
}

.files_content {
  width: 70%;
  padding-left: 1rem;
  overflow: scroll;

}

.files_container {
  padding: 1rem;
  border-left: 1px solid #ccc;
  display: flex;
  justify-content: flex-start;

}
</style>
