plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.android.ksp)
}

android {
    namespace = "com.example.base"
    compileSdk = 35

    defaultConfig {
        minSdk = 30

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt core
    implementation(libs.hilt.android)
//    api(libs.androidx.hilt.work)

    // Compiler (ksp thay kapt)
    ksp(libs.hilt.android.compiler)
    ksp(libs.androidx.hilt.compiler)


    // paging
    api(libs.androidx.paging.runtime)

    // Retrofit
    api(libs.retrofit)
    api(libs.converter.gson)
    api(libs.okhttp)
    api(libs.logging.interceptor)

    // Glide
    api(libs.glide)

    // blurhash
    api(libs.sketch.blurhash)
    api(libs.sketch.view)
    api(libs.sketch.http)
    api(libs.blurhash.android)

    // room
    api(libs.androidx.room.runtime)
    api(libs.androidx.room.paging)
    api(libs.androidx.room.ktx)
}