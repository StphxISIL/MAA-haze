package com.stphxlb.isil.maa.project_03

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.stphxlb.isil.maa.project_03.components.HazeDestination
import com.stphxlb.isil.maa.project_03.data.local.Cart
import com.stphxlb.isil.maa.project_03.data.model.CartLine
import com.stphxlb.isil.maa.project_03.data.model.Product
import com.stphxlb.isil.maa.project_03.presentation.navigation.navigateTo
import com.stphxlb.isil.maa.project_03.presentation.screen.ProductDetailScreen
import com.stphxlb.isil.maa.project_03.ui.theme.Project_03_Theme
import com.stphxlb.isil.maa.project_03.ui.theme.edgeToEdgeDark

// Detalle de un café. Llegas al tocar una tarjeta de la carta.
// No está en la barra de abajo: marca Carta porque se entra desde ahí.
// Los datos viajan en el Intent, uno por uno. No vuelvo a pedir el JSON.
// La pantalla está en presentation/screen/ProductDetailScreen.kt.
class ProductDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edgeToEdgeDark()

        val name = intent.getStringExtra(EXTRA_NAME).orEmpty()
        val category = intent.getStringExtra(EXTRA_CATEGORY).orEmpty()
        val price = intent.getStringExtra(EXTRA_PRICE).orEmpty()
        val description = intent.getStringExtra(EXTRA_DESCRIPTION).orEmpty()
        val available = intent.getBooleanExtra(EXTRA_AVAILABLE, false)

        setContent {
            Project_03_Theme {
                ProductDetailScreen(
                    name = name,
                    category = category,
                    price = price,
                    description = description,
                    available = available,
                    cartCount = Cart.count(),
                    onBackClick = { finish() },
                    onNavigate = { destination -> navigateTo(destination) },
                    onAddClick = { size, quantity ->
                        Cart.add(
                            CartLine(
                                name = name,
                                category = category,
                                size = size,
                                quantity = quantity,
                                price = price
                            )
                        )
                        navigateTo(HazeDestination.ORDER)
                        // cierro el detalle: atrás desde el pedido vuelve a la carta, no a este café
                        finish()
                    }
                )
            }
        }
    }

    companion object {
        private const val EXTRA_NAME = "name"
        private const val EXTRA_CATEGORY = "category"
        private const val EXTRA_PRICE = "price"
        private const val EXTRA_DESCRIPTION = "description"
        private const val EXTRA_AVAILABLE = "available"

        // Arma el Intent para no repetir los putExtra en la carta.
        fun open(activity: Activity, product: Product) {
            val intent = Intent(activity, ProductDetailActivity::class.java)
            intent.putExtra(EXTRA_NAME, product.name)
            intent.putExtra(EXTRA_CATEGORY, product.category)
            intent.putExtra(EXTRA_PRICE, product.price)
            intent.putExtra(EXTRA_DESCRIPTION, product.description)
            intent.putExtra(EXTRA_AVAILABLE, product.available)
            activity.startActivity(intent)
        }
    }
}
