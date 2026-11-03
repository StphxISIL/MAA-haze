package com.stphxlb.isil.maa.project_03.components

import androidx.annotation.DrawableRes
import com.stphxlb.isil.maa.project_03.R

// La foto de cada café según su categoría. La tabla no trae fotos, así que hay una por categoría.
// Son fotos de Unsplash (licencia libre), en res/drawable-nodpi como .webp de 800 px.
// nodpi: Android no las escala por densidad de pantalla; las recorta ContentScale.Crop.
// Si llega una categoría nueva que no está acá, sale la foto de siempre (start_photo).
@DrawableRes
fun drinkPhoto(category: String): Int {
    return when (category) {
        "Espresso" -> R.drawable.drink_espresso
        "Filtro" -> R.drawable.drink_filter
        "Frío" -> R.drawable.drink_cold
        "Té" -> R.drawable.drink_tea
        "Pastelería" -> R.drawable.drink_pastry
        else -> R.drawable.start_photo
    }
}
