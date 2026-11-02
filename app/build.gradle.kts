plugins {
    alias(libs.plugins.android.application) // es una app, no una librería
    alias(libs.plugins.kotlin.compose)      // compilador de Compose
}

android {
    // mismo paquete que project-01: stphxlb / isil / maa
    namespace = "com.stphxlb.isil.maa.project_03"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.stphxlb.isil.maa.project_03" // el id con el que se instala en el celu
        minSdk = 31      // Android 12 para arriba
        targetSdk = 37
        versionCode = 1  // número interno. Si publico de nuevo, este sube
        versionName = "1.0" // el que se ve, "versión 1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false // sin ofuscar mientras sigo en desarrollo
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true // sin esto no compila setContent ni @Composable
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom)) // alinea las versiones de Compose entre sí
    implementation(libs.androidx.activity.compose)      // ComponentActivity y setContent
    implementation(libs.androidx.compose.material3)     // Button, Scaffold, TopAppBar
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // iconos outlined. Home y el carrito están en el set básico; esta línea se quedó del menú anterior
    implementation(libs.androidx.compose.material.icons.extended)

    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling) // para el preview, solo en debug
}
