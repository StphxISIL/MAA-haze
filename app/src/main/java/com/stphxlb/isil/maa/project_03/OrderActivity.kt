package com.stphxlb.isil.maa.project_03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stphxlb.isil.maa.project_03.data.local.Cart
import com.stphxlb.isil.maa.project_03.presentation.navigation.navigateTo
import com.stphxlb.isil.maa.project_03.presentation.screen.OrderScreen
import com.stphxlb.isil.maa.project_03.ui.theme.Project_03_Theme
import com.stphxlb.isil.maa.project_03.ui.theme.edgeToEdgeDark

// Tu pedido, la tercera pantalla de la plantilla. En la barra de abajo es Pedido.
// La Activity le pasa a OrderScreen las líneas de Cart y vacía el carrito cuando se confirma.
// No llama a ningún PHP: no hay tabla de pedidos.
class OrderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edgeToEdgeDark()
        setContent {
            Project_03_Theme {
                OrderScreen(
                    lines = Cart.lines,
                    cartCount = Cart.count(),
                    onNavigate = { destination -> navigateTo(destination) },
                    onConfirm = { Cart.clear() }
                )
            }
        }
    }
}
