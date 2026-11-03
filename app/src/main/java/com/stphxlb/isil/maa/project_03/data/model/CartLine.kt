package com.stphxlb.isil.maa.project_03.data.model

import java.util.Locale

// Una línea del pedido. El precio es el texto del JSON, sin cambiarlo por el tamaño.
// La plantilla muestra S, M y L con precios distintos. Acá la tabla trae un solo precio.
data class CartLine(
    val name: String,
    val size: String,
    val quantity: Int,
    val price: String
)

// "12,50" o "12.50" pasan a número. Si el texto no es un precio, devuelvo 0.
fun String.toPrice(): Double {
    return replace(",", ".").toDoubleOrNull() ?: 0.0
}

fun CartLine.lineTotal(): Double {
    return price.toPrice() * quantity
}

// Dos decimales y el prefijo de soles. Locale.US para que el punto no se vuelva coma.
fun money(amount: Double): String {
    return "S/ " + String.format(Locale.US, "%.2f", amount)
}
