import runs from '../../../Analysis_results/result.json'
import type { AnalysisRunsData } from '@/Types/AnalyisRunsTypes'
const analysisRunsData: AnalysisRunsData = runs as unknown as AnalysisRunsData
export { analysisRunsData }
