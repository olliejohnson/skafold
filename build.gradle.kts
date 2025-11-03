// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    id("com.gradleup.nmcp.aggregation") version "1.2.0"
}

apply {
    from("secrets.gradle.kts")
}

nmcpAggregation {
    centralPortal {
        username = rootProject.extra["central_username"] as String
        password = rootProject.extra["central_password"] as String

        publishingType = "AUTOMATIC"
    }

    publishAllProjectsProbablyBreakingProjectIsolation()
}