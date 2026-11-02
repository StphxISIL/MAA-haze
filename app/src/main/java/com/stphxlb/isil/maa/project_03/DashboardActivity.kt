package com.stphxlb.isil.maa.project_03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stphxlb.isil.maa.project_03.components.ChoiceChip
import com.stphxlb.isil.maa.project_03.components.HazeDestination
import com.stphxlb.isil.maa.project_03.components.HazeScaffold
import com.stphxlb.isil.maa.project_03.ui.theme.AppAccent
import com.stphxlb.isil.maa.project_03.ui.theme.AppForeground
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface
import com.stphxlb.isil.maa.project_03.ui.theme.AppTheme
import com.stphxlb.isil.maa.project_03.ui.theme.Project_03_Theme
import com.stphxlb.isil.maa.project_03.ui.theme.edgeToEdgeDark

// La carta, al estilo de la plantilla Ember. En la barra de abajo es Carta.
// Antes eran 4 recuadros (Insumos, Baristas, Carta, Reportes). Eso era el menú de clase.
// Ahora: saludo, buscador, chips de categoría, una tarjeta grande y una fila.
// Los cafés siguen saliendo del mismo PHP.
class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edgeToEdgeDark()

        var products by mutableStateOf(emptyList<Product>())
        var status by mutableStateOf("")

        setContent {
            Project_03_Theme {
                HomeScreen(
                    products = products,
                    status = status,
                    onProductClick = { product ->
                        ProductDetailActivity.open(this, product)
                    },
                    onNavigate = { destination -> navigateTo(destination) }
                )
            }
        }

        status = getString(R.string.products_loading)
        fetchProducts(
            onReady = { list ->
                products = list
                status = if (list.isEmpty()) getString(R.string.products_empty) else ""
            },
            onError = { message ->
                status = message
            }
        )
    }
}

@Composable
fun HomeScreen(
    products: List<Product>,
    status: String,
    onProductClick: (Product) -> Unit,
    onNavigate: (HazeDestination) -> Unit
) {
    // remember: el texto del buscador y el chip se quedan mientras esta pantalla está abierta
    var query by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    val allLabel = stringResource(R.string.home_all)

    val categories = listOf(allLabel) + products.map { it.category }.distinct()
    val shown = products.filter { product ->
        val categoryOk = category.isEmpty() || product.category == category
        val nameOk = product.name.contains(query, ignoreCase = true)
        categoryOk && nameOk
    }

    HazeScaffold(
        title = stringResource(R.string.products_title),
        selected = HazeDestination.MENU,
        cartCount = Cart.count(),
        onNavigate = onNavigate
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = AppTheme.spacing.large),
            verticalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)
        ) {
            item {
                Text(
                    stringResource(R.string.home_greeting),
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            item {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = { Text(stringResource(R.string.home_search)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null
                        )
                    },
                    shape = RoundedCornerShape(50),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = AppForeground,
                        unfocusedTextColor = AppForeground,
                        focusedBorderColor = AppPrimary,
                        unfocusedBorderColor = AppSurface,
                        cursorColor = AppPrimary,
                        focusedContainerColor = AppSurface,
                        unfocusedContainerColor = AppSurface
                    )
                )
            }

            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)) {
                    items(categories) { label ->
                        val selected = if (category.isEmpty()) label == allLabel else label == category
                        ChoiceChip(
                            label = label,
                            selected = selected,
                            onClick = {
                                category = if (label == allLabel) "" else label
                            }
                        )
                    }
                }
            }

            if (status.isNotEmpty()) {
                item { Text(status) }
            } else if (shown.isEmpty()) {
                item { Text(stringResource(R.string.home_empty_filter)) }
            } else {
                item {
                    FeaturedDrink(
                        product = shown.first(),
                        onClick = { onProductClick(shown.first()) }
                    )
                }

                if (shown.size > 1) {
                    item {
                        Text(
                            stringResource(R.string.home_also),
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    item {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.medium)) {
                            items(shown.drop(1), key = { it.id }) { product ->
                                DrinkCard(
                                    product = product,
                                    onClick = { onProductClick(product) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// La tabla no tiene foto. Uso start_photo en todas las tarjetas.
@Composable
fun FeaturedDrink(product: Product, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(R.drawable.start_photo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.45f))
                .padding(AppTheme.spacing.medium),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(product.category, color = AppAccent)
            Text(
                product.name,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Text(money(product.price.toPrice()), color = AppPrimary)
        }
    }
}

@Composable
fun DrinkCard(product: Product, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(AppSurface)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(R.drawable.start_photo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(AppTheme.spacing.small)) {
            Text(product.name, style = MaterialTheme.typography.titleLarge, maxLines = 2)
            Text(money(product.price.toPrice()), color = AppPrimary)
        }
    }
}
