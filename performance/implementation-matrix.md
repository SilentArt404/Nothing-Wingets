# Implementation matrix

| Requirement | Implemented artifact |
|---|---|
| Event-driven instead of frequent polling | `performance/UpdateStrategy.kt:onEvent` |
| WorkManager with battery/network constraints | `performance/UpdateStrategy.kt:scheduleConstrainedRefresh` |
| Caching + debouncing | `performance/UpdateStrategy.kt:getOrFetch` + `onEvent` |
| Widget redraw/recompute throttling | `widgets/RenderLimiter.kt` |
| Profiling startup/memory/battery | `profiling/perfetto-startup-memory-battery.md` + `scripts/profile_collect.sh` |
| Moto G75 compatibility smoke checks | `smoke/moto-g75-compatibility-checklist.md` + `smoke/moto_g75_smoke.sh` |
| Release with R8/Proguard + regression gate | `release/release-build.gradle.kts`, `release/proguard-rules.pro`, `release/perf-regression-gate.md` |
