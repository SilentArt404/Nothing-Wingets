# Release performance regression gate

Before release candidate sign-off:

1. Build optimized artifact with R8/Proguard enabled.
2. Compare startup/memory/battery metrics against previous stable baseline.
3. Block release if any threshold is exceeded:
   - startup > +5%
   - memory (PSS) > +8%
   - battery drain/hour > +5%
4. Run Moto G75 smoke set and attach outputs.
5. Store all metric artifacts under `artifacts/release/<version>/`.
