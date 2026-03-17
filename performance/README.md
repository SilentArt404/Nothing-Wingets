# Performance update strategy

## What was added
- **Event-driven updates** as the default path (`onEvent`) to avoid high-frequency polling.
- **Constrained WorkManager fallback** (`scheduleConstrainedRefresh`) with:
  - `requiresBatteryNotLow=true`
  - `NetworkType.CONNECTED`
- **Data cache with TTL** (`getOrFetch`) to reduce repeated requests.
- **Debouncing** per event key to collapse update bursts.

## Integration notes
1. Wire launcher callbacks (`onAppWidgetOptionsChanged`, package/broadcast listeners) to `onEvent`.
2. Replace periodic polling loops with event triggers plus fallback worker.
3. Tune TTL by data source type (local: 1-5 min, remote: 10-30 min).
4. Use one unique work tag per widget family to avoid duplicate background jobs.
