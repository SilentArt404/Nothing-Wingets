# Keep widget provider and workers referenced from manifest/reflection
-keep class ** extends android.appwidget.AppWidgetProvider { *; }
-keep class ** extends androidx.work.ListenableWorker { *; }

# Keep serialized models used by widget state cache
-keepclassmembers class com.example.widget.model.** {
    <fields>;
}

# Remove logs in release
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}
