package com.nothing.wingets.performance

data class PerformanceProfile(
    val useResourceShrinking: Boolean,
    val useCodeMinification: Boolean,
    val supportedAbis: List<String>
)

object BuildPerformance {
    val releaseProfile = PerformanceProfile(
        useResourceShrinking = true,
        useCodeMinification = true,
        supportedAbis = listOf("arm64-v8a", "armeabi-v7a", "x86_64")
    )
}
