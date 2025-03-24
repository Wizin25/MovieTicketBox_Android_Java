plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.movieticketbox"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.movieticketbox"
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {


    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.tools.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    // GSON (dùng để chuyển đổi JSON thành đối tượng Java)
    implementation ("com.google.code.gson:gson:2.12.1")
    implementation ("androidx.recyclerview:recyclerview:1.4.0")
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("androidx.cardview:cardview:1.0.0")

     //Zalopayment
    implementation(fileTree(
         mapOf(
             "dir" to "C:\\Users\\PC\\Downloads\\AndroidStudioZaloPay",
             "include" to listOf("*.aar","*.jar"),
             "exclude" to listOf("")
        )
    ))
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.squareup.okhttp3:okhttp:4.6.0")
    implementation("commons-codec:commons-codec:1.18.0")

    implementation ("com.google.android.gms:play-services-maps:19.1.0")
    implementation  ("com.google.android.gms:play-services-location:21.3.0")
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)


}