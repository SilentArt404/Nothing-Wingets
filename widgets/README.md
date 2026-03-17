# Widgets module layout

Each widget now has a dedicated package under `widgets/`:

- `clockdate/`
- `weather/`
- `calendar/`
- `batterystatus/`
- `quicktoggles/`

Every package includes:

- `ui/*Preview.kt` for preview rendering
- `provider/*WidgetProvider.kt` (`AppWidgetProvider`)
- `settings/*SettingsScreen.kt` for detail configuration UI
- `data/*State.kt` state model
- `data/*DataStore.kt` persistence with DataStore

Adaptive widget sizing is centralized in `core/WidgetSize.kt`.
Localization helpers for date/time are in `core/Localization.kt`.
