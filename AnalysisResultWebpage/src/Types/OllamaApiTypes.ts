export interface OllamaGenerateRequest {
  model: string
  prompt: string
  stream?: boolean
  options?: Record<string, any>
  context?: number[]
  raw?: boolean
  images?: string[]
  format?: 'json' | 'text'
  system?: string
  template?: string
  keep_alive?: string
}
