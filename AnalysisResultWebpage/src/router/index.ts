import { createRouter, createWebHistory } from 'vue-router'
import RawRuns from '@/views/RawRuns.vue'
import RawPromptCache from '@/views/RawPromptCache.vue'
const routes = [
  {
    path: '/',
    redirect: '/RawRuns',
  },
  {
    path: '/RawRuns',
    name: 'RawRuns',
    component: RawRuns,
  },
  {
    path: '/RawPrompts',
    name: 'RawPrompts',
    component: RawPromptCache,
  },
  {
    path: '/ModelAvgTimeView',
    name: 'ModelAvgTimeView',
    component: () => import('../views/ModelAvgTimeView.vue'),
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: routes,
})

export default router
