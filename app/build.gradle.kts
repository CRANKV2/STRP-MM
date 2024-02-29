plugins {
    id("com.android.application")
}

android {
    signingConfigs {
        create("STRP") {
            storeFile = file("C:\\Users\\Public\\Documents\\KeyStore\\STRP.jks")
            storePassword = "Francesco.20221994"
            keyAlias = "STRP"
            keyPassword = "Francesco.20221994"
        }
    }
    namespace = "com.strp.magisk.modules"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.strp.magisk.modules"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "0.1 Alpha"

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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.airbnb.android:lottie:4.2.0")
}