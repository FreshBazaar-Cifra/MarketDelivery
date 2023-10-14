plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.devtoolsKsp)
    kotlin("plugin.serialization") version "1.9.10"
    id("kotlin-parcelize")
}

android {
    namespace = "com.marketsvrn.delivery"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.marketsvrn.delivery"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)

    implementation(libs.androidx.core.splashscreen)

    // Kotlin Serialization
    implementation(libs.kotlinx.serialization.json)

    /*// Room
    annotationProcessor(libs.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.room.compiler)*/

    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    /*// Reactive
    implementation(libs.reaktive.reaktive)
    implementation(libs.reaktive.annotations)
    implementation(libs.reaktive.reaktiveTesting)
    implementation(libs.reaktive.coroutinesInterop)*/

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(project(":core:common"))
    implementation(project(":feature:favorites"))
    implementation(project(":feature:login"))
    implementation(project(":feature:register"))
    implementation(project(":feature:addresschange"))

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(project(":core:model"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))

    implementation(project(":feature:logincontinue"))
    implementation(project(":feature:onboarding"))
    implementation(project(":feature:marketselect"))
    implementation(project(":feature:placeselect"))
    implementation(project(":feature:basket"))
    implementation(project(":feature:productdetails"))
    implementation(project(":feature:buyorder"))
    implementation(project(":feature:productslist"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:profilesettings"))
    implementation(project(":feature:orders"))
    implementation(project(":feature:orderdetails"))
}