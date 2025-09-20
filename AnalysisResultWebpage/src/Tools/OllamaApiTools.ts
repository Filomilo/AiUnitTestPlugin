import type { OllamaGenerateRequest } from '@/Types/OllamaApiTypes'

export const createOllamaPromptRequest = (prompt: string, model: string): OllamaGenerateRequest => {
  return {
    model: model,
    prompt: prompt,
    stream: false,
  }
}
