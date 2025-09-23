import type { GeneratedFile } from '@/Types/AnalysisRunsTypes'
import type { TreeNode } from 'primevue/treenode'

export const mapToTreeNode = (file: GeneratedFile, keyPrefix = '0'): TreeNode => {
  return {
    key: keyPrefix,
    label: file.name,
    data: file.content,
    children: file.children?.map((child, idx) => mapToTreeNode(child, `${keyPrefix}-${idx}`)),
    leaf: !file.children || file.children.length === 0,
  }
}
