plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.skpnewsmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.skpnewsmvvm"
        minSdk = 24
        targetSdk = 34
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
    android {
        buildFeatures {
            viewBinding = true
        }
    }
}

dependencies {
    implementation(libs.firebase.auth)
    implementation(libs.activity)
    implementation(libs.firebase.inappmessaging.display)
    implementation(libs.work.runtime)
    val nav_version = "2.7.7"

    // Java language implementation
    implementation("androidx.navigation:navigation-fragment:$nav_version")
    implementation("androidx.navigation:navigation-ui:$nav_version")
    implementation ("com.github.smarteist:autoimageslider:1.4.0")
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    implementation("com.makeramen:roundedimageview:2.3.0")
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    implementation("com.google.firebase:firebase-database")
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.2")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.google.firebase:firebase-messaging-directboot:20.2.0")
    implementation("com.google.firebase:firebase-messaging:23.4.1")
    implementation("com.google.firebase:firebase-messaging-ktx:23.4.1")
}