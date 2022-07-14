plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.demo.feed"
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    kapt(platform(project(":depconstraints")))
    androidTestApi(platform(project(":depconstraints")))
    implementation(project(":app"))
    implementation(project(":shared"))

    // Android KTX
    implementation(Libs.MATERIAL)
    implementation(Libs.NAVIGATION_UI_KTX)
    implementation(Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(Libs.NAVIGATION_DYNAMIC_FRAGMENT_KTX)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    androidTestImplementation(Libs.HILT_TESTING)
    kapt(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)
}