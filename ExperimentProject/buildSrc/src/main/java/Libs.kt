/*
 * Copyright 2020 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

object Libs {

    // Kotlin Library
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7"

    // Android KTX
    const val CORE_KTX = "androidx.core:core-ktx"
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx"
    const val APPCOMPAT = "androidx.appcompat:appcompat"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx"
    const val FRAGMENT_TEST = "androidx.fragment:fragment-testing"

    // Dependency Injection
    const val HILT_ANDROID = "com.google.dagger:hilt-android"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler"
    const val HILT_TESTING = "com.google.dagger:hilt-android-testing"

    // Lifecycle
    const val LIFECYCLE_COMPILER = "androidx.lifecycle:lifecycle-compiler"
    const val LIFECYCLE_LIVE_DATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx"
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx"
    const val LIFECYCLE_VIEW_MODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx"

    // Ui kit
    const val VIEWPAGER2 = "androidx.viewpager2:viewpager2"
    const val SLIDING_PANE_LAYOUT = "androidx.slidingpanelayout:slidingpanelayout"
    const val MATERIAL = "com.google.android.material:material"
    const val DRAWER_LAYOUT = "androidx.drawerlayout:drawerlayout"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout"

    // Animation
    const val LOTTIE = "com.airbnb.android:lottie"

    // Navigation ktx
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx"
    const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx"
    const val NAVIGATION_DYNAMIC_FRAGMENT_KTX = "androidx.navigation:navigation-dynamic-features-fragment"

    // Thread
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core"

    // Database
    const val ROOM_COMPILER = "androidx.room:room-compiler"
    const val ROOM_KTX = "androidx.room:room-ktx"
    const val ROOM_RUNTIME = "androidx.room:room-runtime"

    // Log Unit
    const val TIMBER = "com.jakewharton.timber:timber"

    // Unit test
    const val JUNIT = "junit:junit"
    const val RULES = "androidx.test:rules"
    const val RUNNER = "androidx.test:runner"
    const val EXT_JUNIT = "androidx.test.ext:junit"
    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test"

    // Mock
    const val MOCKITO_CORE = "org.mockito:mockito-core"
    const val MOCKITO_KOTLIN = "com.nhaarman:mockito-kotlin"

    // Pojo
    const val GSON = "com.google.code.gson:gson"

    // Local Storage
    const val DATA_STORE_PREFERENCES = "androidx.datastore:datastore-preferences"

    // Network
    const val OKIO = "com.squareup.okio:okio"
    const val OKHTTP = "com.squareup.okhttp3:okhttp"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor"
    const val RETROFIT_2 = "com.squareup.retrofit2:retrofit"
    const val RETROFIT_2_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson"

}
