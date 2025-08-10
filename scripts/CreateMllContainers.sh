# shellcheck disable=SC2148
docker network create my-net  || true
docker run -d --network my-net --name ollama ollama/ollama