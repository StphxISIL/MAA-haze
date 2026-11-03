package com.stphxlb.isil.maa.project_03.presentation.navigation

import android.app.Activity
import android.content.Intent
import com.stphxlb.isil.maa.project_03.DashboardActivity
import com.stphxlb.isil.maa.project_03.MainActivity
import com.stphxlb.isil.maa.project_03.OrderActivity
import com.stphxlb.isil.maa.project_03.components.HazeDestination

// Qué Activity abre cada botón de la barra de abajo.
// Inicio = portada, Carta = la carta con el JSON, Pedido = el carrito.
fun HazeDestination.activityClass(): Class<out Activity> {
    return when (this) {
        HazeDestination.HOME -> MainActivity::class.java
        HazeDestination.MENU -> DashboardActivity::class.java
        HazeDestination.ORDER -> OrderActivity::class.java
    }
}

// Lo llaman todas las Activities desde onNavigate.
// Si ya estoy ahí, no hago nada. Si no, abro la Activity.
// REORDER_TO_FRONT: si esa pantalla ya estaba abierta, la trae adelante en vez de crear otra.
// Así la carta no vuelve a pedir el JSON y el botón atrás no se llena de copias.
fun Activity.navigateTo(destination: HazeDestination) {
    val target = destination.activityClass()
    if (javaClass == target) return

    val intent = Intent(this, target)
    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    startActivity(intent)
}
