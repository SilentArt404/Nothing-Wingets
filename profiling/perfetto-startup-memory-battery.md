# Profiling plan

## 1) Startup time
- Tooling: Macrobenchmark + Perfetto trace.
- Metric: `timeToInitialDisplayMs` and `fullyDrawnMs` for widget host activity.
- Scenario: cold/warm/hot start, 10 iterations each.

## 2) Memory
- Tooling: Android Studio Memory Profiler + `dumpsys meminfo` snapshots.
- Metric: PSS, Java heap, native heap under:
  - idle (15 min no interaction)
  - active (rapid widget updates for 5 min)

## 3) Battery impact
- Tooling: `dumpsys batterystats`, Battery Historian export.
- Metric: delta in mAh estimate and wakeups in:
  - idle scenario (screen off + background limits)
  - active scenario (screen on + event bursts)

## Acceptance thresholds
- Startup regressions: <= +5% vs baseline.
- Memory regressions: <= +8% PSS vs baseline.
- Battery regressions: <= +5% drain/hour vs baseline for same scenario.
