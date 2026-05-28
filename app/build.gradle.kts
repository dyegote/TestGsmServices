import com.preemptive.dasho.gradle.DashOExtension

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("com.preemptive.dasho.android")


}

//apply(plugin = "com.preemptive.dasho.android")

android {

    namespace = "com.example.testgsmservices"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.testgsmservices"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }


}

dasho {
    home = "/opt/dasho-12.6.0"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(platform("com.google.firebase:firebase-bom:34.8.0"))
    implementation("com.google.android.gms:play-services-gcm:17.0.0")
    implementation("com.google.android.gms:play-services-base:18.3.0")
    implementation("com.google.firebase:firebase-analytics")


    implementation("com.google.android.gms:play-services-identity:17.0.0")
    implementation("com.google.android.gms:play-services-auth:17.0.0")
    implementation("com.google.android.gms:play-services-auth-api-phone:17.0.0")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-perf")



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}