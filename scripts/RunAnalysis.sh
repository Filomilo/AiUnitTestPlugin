#!/usr/bin/env bash
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
chmod +x  "$SCRIPT_DIR"/../AiTestGenerotorAnalisis/gradlew
 "$SCRIPT_DIR"/../AiTestGenerotorAnalisis/gradlew run -x test --scan --no-daemon -Dorg.gradle.jvmargs=-XX:+UseContainerSupport --info
