// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    alias(libs.plugins.android.application) apply false
//    alias(libs.plugins.kotlin.android) apply false
//    alias(libs.plugins.kotlin.compose) apply false
////    alias(libs.plugins.ksp) apply false
//    //hilt
//    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
//    id("com.google.dagger.hilt.android") version "2.56.2" apply false
//}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Standard Android Application plugin
    alias(libs.plugins.android.application) apply false

    // Kotlin plugin for Android
    alias(libs.plugins.kotlin.android) apply false

    // Kotlin Compose plugin (uses the same version as kotlin-android by convention)
    alias(libs.plugins.kotlin.compose) apply false

    // KSP (Kotlin Symbol Processing) plugin - NOW USING THE TOML ALIAS
    alias(libs.plugins.ksp) apply false

    // Hilt Android Gradle plugin
    // You have two ways to do this:

    // Option A: Direct versioning (as you had it, using version from TOML)
    id("com.google.dagger.hilt.android") version libs.versions.hiltAndroid.get() apply false

    // Option B: If you added a Hilt plugin alias to your TOML [plugins] section like:
    // hilt = { id = "com.google.dagger.hilt.android", version.ref = "hiltAndroid" }
    // Then you could use:
    // alias(libs.plugins.hilt) apply false
}