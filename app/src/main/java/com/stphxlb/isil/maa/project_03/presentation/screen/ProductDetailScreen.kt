package com.stphxlb.isil.maa.project_03.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stphxlb.isil.maa.project_03.R
import com.stphxlb.isil.maa.project_03.components.EmberButton
import com.stphxlb.isil.maa.project_03.components.HazeDestination
import com.stphxlb.isil.maa.project_03.components.HazeScaffold
import com.stphxlb.isil.maa.project_03.components.drinkPhoto
import com.stphxlb.isil.maa.project_03.components.QuantityStepper
import com.stphxlb.isil.maa.project_03.components.SizeCard
import com.stphxlb.isil.maa.project_03.data.model.money
import com.stphxlb.isil.maa.project_03.data.model.toPrice
import com.stphxlb.isil.maa.project_03.ui.theme.AppDeep
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppTheme

// Detalle de un café. Lo dibuja ProductDetailActivity con los datos que llegaron en el Intent.
// Tamaño y cantidad son estado de esta pantalla; Agregar avisa con onAddClick y la Activity decide.
@Composable
fun ProductDetailScreen(
    name: String,
    category: String,
    price: String,
    description: String,
    available: Boolean,
    cartCount: Int,
    onBackClick: () -> Unit,
    onNavigate: (HazeDestination) -> Unit,
    onAddClick: (size: String, quantity: Int) -> Unit
) {
    // S, M y L con sus ml, como en la plantilla. El precio no cambia: la tabla trae uno solo.
    val sizes = listOf("S" to 300, "M" to 400, "L" to 500)
    var size by remember { mutableStateOf("M") }
    var quantity by remember { mutableIntStateOf(1) }

    val availableText = if (available) {
        stringResource(R.string.product_yes)
    } else {
        stringResource(R.string.product_no)
    }

    HazeScaffold(
        title = category,
        selected = HazeDestination.MENU,
        cartCount = cartCount,
        onNavigate = onNavigate,
        onBackClick = onBackClick
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = AppTheme.spacing.large)
                .padding(bottom = AppTheme.spacing.large)
        ) {
            Image(
                painter = painterResource(drinkPhoto(category)),
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
                sizes.forEach { (label, volume) ->
                    SizeCard(
                        label = label,
                        volumeMl = volume,
                        selected = label == size,
                        onClick = { size = label }
                    )
                }
            }

            Text(
                stringResource(R.string.detail_quantity),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = AppTheme.spacing.large)
            )
            Row(modifier = Modifier.padding(top = AppTheme.spacing.small)) {
                QuantityStepper(
                    quantity = quantity,
                    onDecrease = { if (quantity > 1) quantity = quantity - 1 },
                    onIncrease = { quantity = quantity + 1 }
                )
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
}
