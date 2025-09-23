<template>
  <div class="prompt-tester">
    <h2>LLM Prompt Tester</h2>
    <div class="card flex justify-center">
      Model
      <Dropdown v-model="selectedModel" :options="models" optionLabel="name" filter placeholder="Select model"
        :maxSelectedLabels="1" class="w-full md:w-80" style="margin: 1rem;" />
    </div>
    <form @submit.prevent="sendPrompt">
      <div>
        <label for="apiUrl">API URL:</label>
        <input v-model="apiUrl" id="apiUrl" type="text" placeholder="Enter API URL" required />
      </div>
      <div>
        <label for="prompt">Prompt:</label>
        <textarea v-model="prompt" id="prompt" rows="4" placeholder="Enter your prompt" required></textarea>
      </div>
      <button type="submit" :disabled="loading">Send Prompt</button>
    </form>
    <div v-if="loading">Sending...</div>
    <div v-if="error" class="error">Error: {{ error }}</div>
    <div v-if="result" class="result">
      <h3>Result:</h3>
      <pre>{{ result }}</pre>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import Dropdown from 'primevue/dropdown';
import { LlmModels } from '@/Data/llmModels';
import { createOllamaPromptRequest } from '@/Tools/OllamaApiTools';

const apiUrl = ref('http://localhost:11434/api/generate')
const prompt = ref('')
const result = ref('')
const loading = ref(false)
const error = ref('')

async function sendPrompt() {
  result.value = ''
  error.value = ''
  loading.value = true
  try {

    const request = createOllamaPromptRequest(prompt.value, (selectedModel.value as any).name);
    console.log("Ollama Request:" + JSON.stringify(request));

    const response = await fetch(apiUrl.value, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(request)
    })
    console.log("Ollama Response:", response);
    if (!response.ok) throw new Error(`HTTP ${response.status}`)
    const data = await response.json()
    console.log("Ollama Data:", data);
    result.value = (data as any).response
  } catch (e) {
    error.value = e.message
  } finally {
    loading.value = false
  }
}




const selectedModel = ref('moondream');
const models = ref(Object.values(LlmModels).map(model => ({ name: model, value: model })));

</script>

<style scoped>
.prompt-tester {
  max-width: 600px;
  margin: 2rem auto;
  padding: 1.5rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  background: #fafafa;
}

.prompt-tester label {
  display: block;
  margin-bottom: 0.25rem;
  font-weight: bold;
}

.prompt-tester input,
.prompt-tester textarea {
  width: 100%;
  margin-bottom: 1rem;
  padding: 0.5rem;
  font-size: 1rem;
}

.prompt-tester button {
  padding: 0.5rem 1.5rem;
  font-size: 1rem;
}

.error {
  color: #b00;
  margin-top: 1rem;
}

.result {
  margin-top: 1rem;
  background: #f4f4f4;
  padding: 1rem;
  border-radius: 4px;
}
</style>
