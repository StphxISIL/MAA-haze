package com.stphxlb.isil.maa.project_03.data.local

import androidx.compose.runtime.mutableStateListOf
import com.stphxlb.isil.maa.project_03.data.model.CartLine

// El carrito vive en memoria mientras la app está abierta.
// No lo guardo en la base: no hay tabla de pedidos.
// mutableStateListOf avisa a Compose cuando agrego o borro, y la pantalla se redibuja.
// Es la única fuente del pedido: las pantallas lo reciben, no lo copian.
object Cart {
    val lines = mutableStateListOf<CartLine>()

    fun add(line: CartLine) {
        lines.add(line)
    }

    fun clear() {
        lines.clear()
    }

    // Cuántos cafés hay en total, sumando las cantidades. Es el número del globo de arriba.
    fun count(): Int {
        return lines.sumOf { it.quantity }
    }
}
