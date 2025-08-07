 docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
 docker exec ollama ollama pull llama3.2-vision
docker exec ollama ollama pull gemma3:1b
# docker exec ollama ollama pull gemma3
# docker exec ollama ollama pull gemma3:12b
# docker exec ollama ollama pull deepseek-r1
# docker exec ollama ollama pull llama3.2
# docker exec ollama ollama pull llama3.2:1b
# docker exec ollama ollama pull llama3.1
# docker exec ollama ollama pull phi4
# docker exec ollama ollama pull phi4-mini
# docker exec ollama ollama pull mistral
# docker exec ollama ollama pull moondream
# docker exec ollama ollama pull neural-chat
# docker exec ollama ollama pull starling-lm
# docker exec ollama ollama pull codellama
# docker exec ollama ollama pull llama2-uncensored
# docker exec ollama ollama pull llava
# docker exec ollama ollama pull granite3.3

docker stop ollama
docker rm ollama