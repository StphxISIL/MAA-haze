// Gradle de la raíz. apply false: acá solo los declaro.
// El que los prende de verdad es app/build.gradle.kts.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
}