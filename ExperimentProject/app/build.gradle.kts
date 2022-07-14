import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.demo.expj"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        namespace = "com.demo.expj"

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            versionNameSuffix = "-debug"
        }

        maybeCreate("staging")
        getByName("staging") {
            versionNameSuffix = "-staging"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // To avoid the compile error: "Cannot inline bytecode built with JVM target 1.8
    // into bytecode that is being built with JVM target 1.6"
    kotlinOptions {
        val options = this as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
        options.jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    dynamicFeatures += setOf(":feature:feed")

}

kapt {
    // @see: https://dagger.dev/hilt/flags
    arguments {
        arg("dagger.hilt.shareTestComponents", "true")
    }
    // Allow references to generated code
    correctErrorTypes = true
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))
    androidTestApi(platform(project(":depconstraints")))

    // Ui Kit
    implementation(Libs.MATERIAL)
    implementation(Libs.NAVIGATION_UI_KTX)
    implementation(Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(Libs.NAVIGATION_DYNAMIC_FRAGMENT_KTX)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    androidTestImplementation(Libs.HILT_TESTING)
    kapt(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)

    // Network
    implementation(Libs.OKHTTP)
    implementation(Libs.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Libs.OKIO)
    implementation(Libs.RETROFIT_2)
    implementation(Libs.RETROFIT_2_CONVERTER_GSON)
}