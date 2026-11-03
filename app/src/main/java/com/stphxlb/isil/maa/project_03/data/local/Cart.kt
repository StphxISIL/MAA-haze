package com.stphxlb.isil.maa.project_03.data.local

import androidx.compose.runtime.mutableStateListOf
import com.stphxlb.isil.maa.project_03.data.model.CartLine

// El carrito vive en memoria mientras la app está abierta.
// No lo guardo en la base: no hay tabla de pedidos.
// mutableStateListOf avisa a Compose cuando agrego o borro, y la pantalla se redibuja.
// Es la única fuente del pedido: las pantallas lo reciben, no lo copian.
object Cart {
    val lines = mutableStateListOf<CartLine>()

    // Si ese café ya está con ese tamaño, sumo la cantidad en vez de repetir la línea.
    fun add(line: CartLine) {
        val index = lines.indexOfFirst { it.name == line.name && it.size == line.size }
        if (index == -1) {
            lines.add(line)
        } else {
            lines[index] = lines[index].copy(quantity = lines[index].quantity + line.quantity)
        }
    }

    // CartLine no se cambia por dentro (val): se reemplaza por una copia con otra cantidad.
    // Asignar lines[index] es lo que avisa a Compose.
    fun increase(index: Int) {
        lines[index] = lines[index].copy(quantity = lines[index].quantity + 1)
    }

    // Con 1 no baja a 0: para quitar el café está la X.
    fun decrease(index: Int) {
        if (lines[index].quantity > 1) {
            lines[index] = lines[index].copy(quantity = lines[index].quantity - 1)
        }
    }

    fun remove(index: Int) {
        lines.removeAt(index)
    }

    fun clear() {
        lines.clear()
    }

    // Cuántos cafés hay en total, sumando las cantidades. Es el número del globo de arriba.
    fun count(): Int {
        return lines.sumOf { it.quantity }
    }
}
