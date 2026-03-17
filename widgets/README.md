# Widget redraw/state recompute limits

Use `RenderLimiter.shouldRender(...)` before pushing `RemoteViews` updates:

```kotlin
if (renderLimiter.shouldRender(widgetId, currentState.hashCode(), 1_500)) {
    appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
}
```

Guidelines:
- Compute a deterministic `stateHash` from visible state only.
- Keep `minRenderIntervalMs` between `1000..3000` for smoothness vs battery.
- Skip expensive recompute paths if hash did not change.
- Batch multiple incoming events with shared debounce from `performance/UpdateStrategy`.
