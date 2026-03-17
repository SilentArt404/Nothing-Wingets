#!/usr/bin/env bash
set -euo pipefail

OUT_DIR=${1:-artifacts/profile}
PKG=${2:-com.example.widget}
mkdir -p "$OUT_DIR"

adb shell am force-stop "$PKG" || true
adb shell am start -n "$PKG/.MainActivity" >/dev/null
adb shell dumpsys meminfo "$PKG" > "$OUT_DIR/meminfo.txt"
adb shell dumpsys batterystats --charged > "$OUT_DIR/batterystats.txt"

echo "Profile artifacts saved to $OUT_DIR"
