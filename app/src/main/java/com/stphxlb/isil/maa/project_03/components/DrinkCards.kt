package com.stphxlb.isil.maa.project_03.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.stphxlb.isil.maa.project_03.data.model.Product
import com.stphxlb.isil.maa.project_03.R
import com.stphxlb.isil.maa.project_03.data.model.money
import com.stphxlb.isil.maa.project_03.data.model.toPrice
import com.stphxlb.isil.maa.project_03.ui.theme.AppAccent
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface
import com.stphxlb.isil.maa.project_03.ui.theme.AppTheme

// Las dos tarjetas de café de la carta. Estaban dentro de DashboardActivity.
// Reciben un Product y avisan con onClick; no saben qué pantalla se abre después.
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
