import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.remap"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.remap"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "MAPKIT_API_KEY", gradleLocalProperties(rootDir, providers).getProperty("MAPKIT_API_KEY"))
        buildConfigField("String", "GEOCODER_API_KEY", gradleLocalProperties(rootDir, providers).getProperty("GEOCODER_API_KEY"))
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kapt {
        correctErrorTypes = true
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.io.coil)
    implementation(libs.androidx.core.splashscreen)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.material)
    implementation(libs.androidx.material3)
    implementation(libs.material.icons.core)
    implementation(libs.material.icons.extended)
    implementation(libs.yandex.mapkit)
    implementation(libs.androidx.material.android)
    implementation(libs.androidx.hilt.compose)
    implementation(libs.retrofit)
    implementation(libs.okhttp.interceptor)
    implementation(libs.custom.calendar)
    implementation(libs.compose.webview)
    implementation(libs.accompanist)
    implementation(libs.javapoet)
    implementation(libs.javax.inject)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.adapters.result)
    implementation(libs.security.crypto)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.androidx.security.crypto.ktx)


    kapt(libs.hilt.compiler)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(project(":features:home"))
    implementation(project(":features:map"))
    implementation(project(":features:events"))
    implementation(project(":features:profile"))
    implementation(project(":features:auth"))

    implementation(project(":data:recyclepoint"))
    implementation(project(":data:profile"))
    implementation(project(":data:events"))
    implementation(project(":data:community"))
    implementation(project(":data:auth"))

    implementation(project(":core:ui"))
    implementation(project(":core:data"))

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}