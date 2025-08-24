#!/usr/bin/env bash
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

 "$SCRIPT_DIR"/../AiTestGenerotorAnalisis/gradlew test --scan --no-daemon -Dorg.gradle.jvmargs=-XX:+UseContainerSupport