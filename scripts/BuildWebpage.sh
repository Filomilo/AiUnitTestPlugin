SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
apt-get update
apt-get install -y curl
curl -fsSL https://deb.nodesource.com/setup_22.x | sudo -E bash -
apt-get install -y nodejs
yarn --cwd "$SCRIPT_DIR"/../AnalysisResultWebpage/ install
yarn --cwd "$SCRIPT_DIR"/../AnalysisResultWebpage/ build