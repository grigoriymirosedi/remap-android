@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.map"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.core.ktx)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.material.icons.core)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.material.icons.extended)
    implementation(libs.androidx.material3.android)
    implementation(libs.io.coil)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.retrofit)

    implementation(libs.yandex.mapkit)
    implementation(libs.accompanist.permission)

    implementation(libs.androidx.hilt.compose)
    kapt(libs.hilt.compiler)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(project(":data:recyclepoint"))
    implementation(project(":core:ui"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}