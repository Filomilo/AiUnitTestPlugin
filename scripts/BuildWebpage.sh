SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
apt-get update
apt-get install -y curl

curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.35.3/install.sh | bash

# Download and install nvm:
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.40.3/install.sh | bash

# in lieu of restarting the shell
\. "$HOME/.nvm/nvm.sh"

# Download and install Node.js:
nvm install 22

# Verify the Node.js version:
node -v # Should print "v22.19.0".

# Download and install Yarn:
corepack enable yarn

# Verify Yarn version:
yarn -v





yarn --cwd "$SCRIPT_DIR"/../AnalysisResultWebpage/ install
yarn --cwd "$SCRIPT_DIR"/../AnalysisResultWebpage/ build