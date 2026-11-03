package com.stphxlb.isil.maa.project_03.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.stphxlb.isil.maa.project_03.R
import com.stphxlb.isil.maa.project_03.data.model.CartLine
import com.stphxlb.isil.maa.project_03.data.model.lineTotal
import com.stphxlb.isil.maa.project_03.data.model.money
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSecondary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface
import com.stphxlb.isil.maa.project_03.ui.theme.AppTheme

// Una línea del pedido, como en la pantalla Checkout de la plantilla:
// foto, nombre, tamaño y precio, los botones - y + (QuantityStepper) y la X para quitarla.
// No toca el carrito: avisa con onDecrease, onIncrease y onRemove y la pantalla decide.
@Composable
fun CartLineRow(
    line: CartLine,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(AppSurface)
            .padding(AppTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
    ) {
        Image(
            painter = painterResource(R.drawable.start_photo),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(14.dp)),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                line.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis // un nombre largo termina en … y no empuja los botones
            )
            Text(stringResource(R.string.order_line_size, line.size), color = AppSecondary)
            Text(money(line.lineTotal()), color = AppPrimary)
        }
        Column(horizontalAlignment = Alignment.End) {
            IconButton(onClick = onRemove, modifier = Modifier.size(32.dp)) {
                Icon(Icons.Outlined.Close, contentDescription = stringResource(R.string.order_remove))
            }
            QuantityStepper(
                quantity = line.quantity,
                onDecrease = onDecrease,
                onIncrease = onIncrease
            )
        }
    }
}
