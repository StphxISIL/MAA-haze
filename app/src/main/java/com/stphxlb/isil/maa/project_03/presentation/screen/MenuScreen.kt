package com.stphxlb.isil.maa.project_03.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import com.stphxlb.isil.maa.project_03.R
import com.stphxlb.isil.maa.project_03.components.ChoiceChip
import com.stphxlb.isil.maa.project_03.components.DrinkCard
import com.stphxlb.isil.maa.project_03.components.EmberButton
import com.stphxlb.isil.maa.project_03.components.FeaturedDrink
import com.stphxlb.isil.maa.project_03.components.HazeDestination
import com.stphxlb.isil.maa.project_03.components.HazeScaffold
import com.stphxlb.isil.maa.project_03.data.model.Product
import com.stphxlb.isil.maa.project_03.ui.theme.AppForeground
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface
import com.stphxlb.isil.maa.project_03.ui.theme.AppTheme

// La carta, Carta en la barra de abajo. La dibuja DashboardActivity.
// Antes se llamaba HomeScreen; ahora se llama como su destino, HazeDestination.MENU.
// Saludo, buscador, chips de categoría, una tarjeta grande y una fila con los demás.
@Composable
fun MenuScreen(
    products: List<Product>,
    cartCount: Int,
    status: String,
    failed: Boolean,
    onRetry: () -> Unit,
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
        cartCount = cartCount,
        onNavigate = onNavigate
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = AppTheme.spacing.large),
            contentPadding = PaddingValues(vertical = AppTheme.spacing.medium),
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
                // Solo si falló la red: vuelve a pedir el JSON sin salir de la carta
                if (failed) {
                    item { EmberButton(text = stringResource(R.string.products_retry), onClick = onRetry) }
                }
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
