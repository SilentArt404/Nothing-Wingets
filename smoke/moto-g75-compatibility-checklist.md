# Moto G75 compatibility smoke set

## Device class assumptions
- Android: 14 (verify exact patch level on target firmware)
- Density buckets to validate: 420/440 dpi
- Launcher grid variants to validate: 4x5, 4x6, 5x6
- Background behavior: OEM battery saver + restricted background app mode

## Smoke checks
1. **Android version gate**
   - Ensure min/target SDK behavior is compatible with Android 14 restrictions.
2. **DPI/layout check**
   - Validate widget content does not clip at 420/440 dpi.
3. **Launcher grid check**
   - Validate min/max resize spans on 4x5, 4x6, 5x6 home grids.
4. **Background restriction check**
   - Put app in restricted mode and ensure WorkManager constrained jobs eventually run.
5. **Idle resilience check**
   - 8h idle overnight, verify no stale widget state on wake.
