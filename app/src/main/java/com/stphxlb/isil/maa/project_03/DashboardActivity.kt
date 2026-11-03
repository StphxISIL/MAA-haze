package com.stphxlb.isil.maa.project_03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.stphxlb.isil.maa.project_03.data.local.Cart
import com.stphxlb.isil.maa.project_03.data.model.Product
import com.stphxlb.isil.maa.project_03.data.remote.fetchProducts
import com.stphxlb.isil.maa.project_03.presentation.navigation.navigateTo
import com.stphxlb.isil.maa.project_03.presentation.screen.MenuScreen
import com.stphxlb.isil.maa.project_03.ui.theme.Project_03_Theme
import com.stphxlb.isil.maa.project_03.ui.theme.edgeToEdgeDark

// La carta, al estilo de la plantilla Ember. En la barra de abajo es Carta.
// La Activity tiene el estado de la carta (products y status) y pide el JSON a data/remote.
// La pantalla está en presentation/screen/MenuScreen.kt: recibe el estado y avisa los toques.
class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edgeToEdgeDark()

        var products by mutableStateOf(emptyList<Product>())
        var status by mutableStateOf("")
        var failed by mutableStateOf(false) // true si no llegó el JSON: la carta muestra Reintentar

        // Pide la carta. Se llama al abrir y cada vez que se toca Reintentar.
        fun load() {
            failed = false
            status = getString(R.string.products_loading)
            fetchProducts(
                onReady = { list ->
                    products = list
                    status = if (list.isEmpty()) getString(R.string.products_empty) else ""
                },
                onError = { message ->
                    status = message
                    failed = true
                }
            )
        }

        setContent {
            Project_03_Theme {
                MenuScreen(
                    products = products,
                    cartCount = Cart.count(),
                    status = status,
                    failed = failed,
                    onRetry = { load() },
                    onProductClick = { product ->
                        ProductDetailActivity.open(this, product)
                    },
                    onNavigate = { destination -> navigateTo(destination) }
                )
            }
        }

        load()
    }
}
