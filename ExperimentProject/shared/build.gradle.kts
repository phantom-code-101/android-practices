plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    kapt(platform(project(":depconstraints")))
    androidTestApi(platform(project(":depconstraints")))

    // Log
    implementation(Libs.TIMBER)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    androidTestImplementation(Libs.HILT_TESTING)
    kapt(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)

    // Database
    implementation(Libs.ROOM_KTX)
    implementation(Libs.ROOM_RUNTIME)
    implementation(Libs.ROOM_COMPILER)

    // Coroutines
    implementation(Libs.COROUTINES)
    implementation(Libs.COROUTINES_TEST)

    // Network
    implementation(Libs.OKHTTP)
    implementation(Libs.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Libs.OKIO)
    implementation(Libs.RETROFIT_2)
    implementation(Libs.RETROFIT_2_CONVERTER_GSON)

}