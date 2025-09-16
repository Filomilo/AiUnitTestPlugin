import cache from '../../../LLMcache.cache?raw'
import type { PromptResult } from '@/Types/AnalyisRunsTypes'
const LLMCache: PromptResult[] = JSON.parse(cache as string) as unknown as PromptResult[]
export { LLMCache }
