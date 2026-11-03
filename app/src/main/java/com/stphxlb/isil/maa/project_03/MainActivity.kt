package com.stphxlb.isil.maa.project_03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stphxlb.isil.maa.project_03.data.local.Cart
import com.stphxlb.isil.maa.project_03.presentation.navigation.navigateTo
import com.stphxlb.isil.maa.project_03.presentation.screen.MainScreen
import com.stphxlb.isil.maa.project_03.ui.theme.Project_03_Theme
import com.stphxlb.isil.maa.project_03.ui.theme.edgeToEdgeDark

// Portada. Es la que sale al tocar el icono (LAUNCHER en el Manifest).
// La Activity solo conecta: le da a MainScreen el carrito y le dice cómo navegar.
// La pantalla está en presentation/screen/MainScreen.kt.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // siempre primero, si no la activity no arranca bien
        edgeToEdgeDark()
        setContent {
            Project_03_Theme { // sin esto no agarran mis colores ni DM Sans
                MainScreen(
                    cartCount = Cart.count(),
                    onNavigate = { destination -> navigateTo(destination) }
                )
            }
        }
    }
}
