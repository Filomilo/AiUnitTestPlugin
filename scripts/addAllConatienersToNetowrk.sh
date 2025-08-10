for container in $(docker ps -q); do
  docker network connect ollama-net "$container" || echo "Container $container is already connected or failed"
done
