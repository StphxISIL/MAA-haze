package com.stphxlb.isil.maa.project_03.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Márgenes de la app. En las pantallas escribo AppTheme.spacing.large
// en vez de poner 24.dp suelto. Si quiero más aire, lo subo acá y cambia en todos lados.
data class AppSpacing(
    val small: Dp = 8.dp,       // entre el título y el subtítulo, o entre recuadros
    val medium: Dp = 12.dp,     // padding de términos y de la carta
    val large: Dp = 24.dp,      // el margen normal de una pantalla
    val extraLarge: Dp = 48.dp  // la frase de la portada y el texto sobre la foto
)

// Tamaños que se repiten y no son un padding.
data class AppComponentSizes(
    val logoSizeLarge: Dp = 220.dp // la taza de la portada. La subí para que se lea el trazo.
)

// CompositionLocal es la forma de pasar un valor por todo el árbol de Compose
// sin tener que mandarlo de función en función.
// Theme.kt los publica. Las pantallas los leen con AppTheme.spacing y AppTheme.sizes.
val LocalAppSpacing = staticCompositionLocalOf { AppSpacing() }
val LocalAppComponentSizes = staticCompositionLocalOf { AppComponentSizes() }
