/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("java-platform")
    id("maven-publish")
}

dependencies {

    // All Dependency Version
    val appcompat = "1.1.0"
    val activity = "1.2.0-rc01"
    val core = "1.3.2"
    val constraintLayout = "1.1.3"
    val material = "1.4.0-beta01"
    val fragment = "1.3.0"
    val coroutines = "1.6.0"
    val coroutinesTest = "1.6.0"
    val lifecycle = "2.4.0-alpha01"
    val room = "2.4.2"
    val dataStore = "1.0.0-beta01"
    val junit = "4.13"
    val junitExt = "1.1.1"
    val rules = "1.1.1"
    val runner = "1.2.0"
    val mockito = "3.3.1"
    val mockitoKotlin = "1.5.0"
    val okhttp = "3.10.0"
    val okio = "1.14.0"
    val retrofit = "2.9.0"
    val retrofitConverterGson = "2.9.0"
    val gson = "2.8.6"
    val timber = "4.7.1"
    val hilt = Versions.HILT_AGP

    // @see https://blog.mrhaki.com/2019/04/gradle-goodness-manage-dependency.html
    // A dependency constraint can be used to define the version
    constraints {
        api("${Libs.KOTLIN_STDLIB}:${Versions.KOTLIN}")
        api("${Libs.ACTIVITY_KTX}:$activity")
        api("${Libs.APPCOMPAT}:$appcompat")
        api("${Libs.CORE_KTX}:$core")
        api("${Libs.MATERIAL}:$material")
        api("${Libs.CONSTRAINT_LAYOUT}:$constraintLayout")

        // Navigation KTX
        api("${Libs.NAVIGATION_UI_KTX}:${Versions.NAVIGATION}")
        api("${Libs.NAVIGATION_FRAGMENT_KTX}:${Versions.NAVIGATION}")
        api("${Libs.NAVIGATION_DYNAMIC_FRAGMENT_KTX}:${Versions.NAVIGATION}")

        // Lifecycle
        api("${Libs.LIFECYCLE_COMPILER}:$lifecycle")
        api("${Libs.LIFECYCLE_LIVE_DATA_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_RUNTIME_KTX}:$lifecycle")
        api("${Libs.LIFECYCLE_VIEW_MODEL_KTX}:$lifecycle")

        // Local Storage
        api("${Libs.DATA_STORE_PREFERENCES}:$dataStore")

        // Database
        api("${Libs.ROOM_KTX}:$room")
        api("${Libs.ROOM_RUNTIME}:$room")
        api("${Libs.ROOM_COMPILER}:$room")

        // Dependency Injection
        api("${Libs.HILT_ANDROID}:$hilt")
        api("${Libs.HILT_COMPILER}:$hilt")
        api("${Libs.HILT_TESTING}:$hilt")

        // Log
        api("${Libs.TIMBER}:$timber")

        // Unit Test
        api("${Libs.JUNIT}:$junit")
        api("${Libs.EXT_JUNIT}:$junitExt")
        api("${Libs.RULES}:$rules")
        api("${Libs.RUNNER}:$runner")

        // Mock tools
        api("${Libs.MOCKITO_CORE}:$mockito")
        api("${Libs.MOCKITO_KOTLIN}:$mockitoKotlin")

        // Coroutines
        api("${Libs.COROUTINES}:${coroutines}")
        api("${Libs.COROUTINES_TEST}:${coroutines}")

        // Network
        api("${Libs.GSON}:$gson")
        api("${Libs.OKIO}:$okio")
        api("${Libs.OKHTTP}:$okhttp")
        api("${Libs.OKHTTP_LOGGING_INTERCEPTOR}:$okhttp")
        api("${Libs.RETROFIT_2}:$retrofit")
        api("${Libs.RETROFIT_2_CONVERTER_GSON}:$retrofitConverterGson")

    }

}

publishing {
    publications {
        create<MavenPublication>("myPlatform") {
            from(components["javaPlatform"])
        }
    }
}
