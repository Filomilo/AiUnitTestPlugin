#!/usr/bin/env bash
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
chmod +x  "$SCRIPT_DIR"/../AiTestGenerotorAnalisis/gradlew
echo "------------------run dradlew"
"$SCRIPT_DIR"/../AiTestGenerotorAnalisis/gradlew run -x test --scan --no-daemon \
  -Dorg.gradle.jvmargs="-Xmx1g -XX:+UseContainerSupport" \
  --info