package com.stphxlb.isil.maa.project_03.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.stphxlb.isil.maa.project_03.R

// DM Sans, la fuente de la plantilla Ember. Los .ttf están en res/font.
// FontWeight elige el archivo: Regular, Medium o Bold.
val dmSansFamily = FontFamily(
    Font(R.font.dm_sans_regular, FontWeight.Normal),
    Font(R.font.dm_sans_medium, FontWeight.Medium),
    Font(R.font.dm_sans_bold, FontWeight.Bold)
)

// Estilos que Theme.kt le pasa a MaterialTheme.
// En las pantallas los pido con MaterialTheme.typography.displayLarge, etc.
// displayLarge  = el nombre "Haze" de la portada
// headlineLarge = el saludo de la carta y el nombre del café en el detalle
// titleLarge    = títulos de la barra de arriba y el nombre de cada café en las tarjetas
// bodyLarge     = el texto normal, cuando no pongo otro style
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = dmSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 64.sp,
        lineHeight = 72.sp,
        letterSpacing = (-1).sp
    ),
    headlineLarge = TextStyle(
        fontFamily = dmSansFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = dmSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = dmSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.2.sp
    )
)
