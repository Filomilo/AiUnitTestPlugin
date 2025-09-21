export interface AnalysisRunsData {
  runs: Run[]
  fails: Fail[]
}

export interface Run {
  type: string
  llmModel: string
  project: string
  strategy: string
  strategyDescription: string
  time: string
  report: Report
  deviceSpecification: DeviceSpecification
  duration: string
  executionLogs: string[]
  promptResults: PromptResult[]
  generatedFiles: GeneratedFile[]
  fails: Fail[]
  warnings?: Warning[]
}

export interface Report {
  CoveragePercent: number
}

export interface DeviceSpecification {
  ramBytes: number
  system: string
  processor: Processor
  gpu: GPU[]
}

export interface Processor {
  name: string
  physicalCores: number
  logicalCores: number
}

export interface GPU {
  name: string
  VRamBytes: number
}

export interface PromptResult {
  prompt: string
  response: string
  modelName: string
  generationTime: string
  deviceSpecification: DeviceSpecification
}

export interface GeneratedFile {
  name: string
  children: GeneratedFile[]
  content: string
}

export interface Fail {
  type: string
  failureReason: FailureReason
  llmModel: string
  project: string
  strategy: string
  time: string
  deviceSpecification: DeviceSpecification
  warnings?: Warning[]
}

export interface FailureReason {
  message: string
}

export interface Warning {
  type: string
  message: string
}
