package com.stphxlb.isil.maa.project_03.ui.theme

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

// El tema. Cada Activity envuelve su pantalla con Project_03_Theme { ... }.
// Acá se juntan los colores (Color.kt), la fuente (Type.kt) y los márgenes (Spacing.kt).

// Paleta oscura de Ember. "on" es el color de lo que va ENCIMA de ese fondo.
private val LightColorScheme = lightColorScheme(
    primary = AppPrimary,           // botones ámbar
    onPrimary = AppBackground,      // texto del botón, el marrón oscuro
    secondary = AppSecondary,
    tertiary = AppAccent,
    background = AppBackground,     // el fondo casi negro
    onBackground = AppForeground,   // texto crema
    surface = AppBackground,
    onSurface = AppForeground
)

// La misma paleta. Si el celular pide modo oscuro, no se invierte a crema.
private val DarkColorScheme = darkColorScheme(
    primary = AppPrimary,
    onPrimary = AppBackground,
    secondary = AppSecondary,
    tertiary = AppAccent,
    background = AppBackground,
    onBackground = AppForeground,
    surface = AppBackground,
    onSurface = AppForeground
)

@Composable
fun Project_03_Theme(
    // false a propósito. Las dos paletas ya son las de Ember, así que da igual.
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false, // false = mis colores, no los del wallpaper del celu
    content: @Composable () -> Unit // la pantalla que envuelvo
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme // este es el que queda, con los dos flags en false
    }

    // sin este provider, AppTheme.spacing y AppTheme.sizes no existen en las pantallas
    CompositionLocalProvider(
        LocalAppSpacing provides AppSpacing(),
        LocalAppComponentSizes provides AppComponentSizes()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography // DM Sans, definido en Type.kt
        ) {
            // Sin Surface, Text e Icon salen negros: Compose no hereda onBackground solo.
            // contentColor es el crema. Lo que pinta su propio color (el ámbar del botón) no cambia.
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = colorScheme.background,
                contentColor = colorScheme.onBackground,
                content = content
            )
        }
    }
}

// Los iconos de la hora quedan claros. El fondo es oscuro y si no, no se leen.
fun ComponentActivity.edgeToEdgeDark() {
    enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.dark(0xFF1C140C.toInt()),
        navigationBarStyle = SystemBarStyle.dark(0xFF1C140C.toInt())
    )
}

// Atajo para no escribir LocalAppSpacing.current en cada pantalla.
// Uso: Modifier.padding(AppTheme.spacing.large)
object AppTheme {
    val spacing: AppSpacing
        @Composable @ReadOnlyComposable
        get() = LocalAppSpacing.current

    val sizes: AppComponentSizes
        @Composable @ReadOnlyComposable
        get() = LocalAppComponentSizes.current
}
