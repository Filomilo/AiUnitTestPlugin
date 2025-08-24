
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"


for f in "$SCRIPT_DIR"/*.sh; do
        dos2unix "$f"
        chmod +x "$f"
done 



"$SCRIPT_DIR"/UpdateLexers.sh
