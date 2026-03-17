#!/usr/bin/env bash
set -euo pipefail

PASS=1

android_release=$(adb shell getprop ro.build.version.release | tr -d '\r')
density=$(adb shell wm density | tr -d '\r')
size=$(adb shell wm size | tr -d '\r')

if [[ "$android_release" != "14"* ]]; then
  echo "[FAIL] Android release expected 14.x, got: $android_release"
  PASS=0
else
  echo "[OK] Android release: $android_release"
fi

if [[ "$density" != *"420"* && "$density" != *"440"* ]]; then
  echo "[WARN] Unexpected density for Moto G75 class: $density"
else
  echo "[OK] Density looks compatible: $density"
fi

echo "[INFO] Display size: $size"

echo "[INFO] Manual validations required: launcher grid (4x5/4x6/5x6), background restricted mode"

if [[ "$PASS" -eq 1 ]]; then
  echo "Smoke checks finished: PASS-with-manual-steps"
  exit 0
fi

echo "Smoke checks finished: FAIL"
exit 1
