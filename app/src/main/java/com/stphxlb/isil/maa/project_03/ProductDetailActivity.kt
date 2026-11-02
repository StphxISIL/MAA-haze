package com.stphxlb.isil.maa.project_03

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stphxlb.isil.maa.project_03.ui.ChoiceChip
import com.stphxlb.isil.maa.project_03.ui.EmberButton
import com.stphxlb.isil.maa.project_03.ui.theme.AppDeep
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppTheme
import com.stphxlb.isil.maa.project_03.ui.theme.Project_03_Theme
import com.stphxlb.isil.maa.project_03.ui.theme.edgeToEdgeDark

// Detalle de un café. Llegas al tocar una tarjeta de la home.
// Los datos viajan en el Intent, uno por uno. No vuelvo a pedir el JSON.
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
                    onBackClick = { finish() },
                    onAddClick = { size, quantity ->
                        Cart.add(
                            CartLine(
                                name = name,
                                size = size,
                                quantity = quantity,
                                price = price
                            )
                        )
                        startActivity(Intent(this, OrderActivity::class.java))
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

        // Arma el Intent para no repetir los putExtra en la home.
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

@Composable
fun ProductDetailScreen(
    name: String,
    category: String,
    price: String,
    description: String,
    available: Boolean,
    onBackClick: () -> Unit,
    onAddClick: (size: String, quantity: Int) -> Unit
) {
    // S, M y L salen de la plantilla. El precio no cambia: la tabla trae uno solo.
    val sizes = listOf("S", "M", "L")
    var size by remember { mutableStateOf("M") }
    var quantity by remember { mutableIntStateOf(1) }

    val availableText = if (available) {
        stringResource(R.string.product_yes)
    } else {
        stringResource(R.string.product_no)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .padding(AppTheme.spacing.large)
            .navigationBarsPadding()
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        }

        Image(
            painter = painterResource(R.drawable.start_photo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )

        Text(
            name,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(top = AppTheme.spacing.medium)
        )
        Text(money(price.toPrice()), color = AppPrimary)
        Text(
            stringResource(R.string.field_category) + ": " + category,
            modifier = Modifier.padding(top = AppTheme.spacing.small)
        )
        Text(stringResource(R.string.field_description) + ": " + description)
        Text(
            stringResource(R.string.field_available) + ": " + availableText,
            color = if (available) AppPrimary else AppDeep
        )

        Text(
            stringResource(R.string.detail_size),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = AppTheme.spacing.large)
        )
        Row(
            modifier = Modifier.padding(top = AppTheme.spacing.small),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
        ) {
            sizes.forEach { option ->
                ChoiceChip(
                    label = option,
                    selected = option == size,
                    onClick = { size = option }
                )
            }
        }

        Text(
            stringResource(R.string.detail_quantity),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = AppTheme.spacing.large)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (quantity > 1) quantity = quantity - 1 }) {
                Icon(Icons.Outlined.Remove, contentDescription = null)
            }
            Text(quantity.toString())
            IconButton(onClick = { quantity = quantity + 1 }) {
                Icon(Icons.Outlined.Add, contentDescription = null)
            }
        }

        // Si disponible es 0, el botón no responde.
        EmberButton(
            text = if (available) {
                stringResource(R.string.detail_add)
            } else {
                stringResource(R.string.detail_sold_out)
            },
            enabled = available,
            onClick = { onAddClick(size, quantity) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AppTheme.spacing.large)
        )
    }
}
