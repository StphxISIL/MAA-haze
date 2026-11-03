package com.stphxlb.isil.maa.project_03.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stphxlb.isil.maa.project_03.R
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface

// Los botones - y + con el número al medio, como en la plantilla.
// No guarda la cantidad: la recibe y avisa con onDecrease y onIncrease.
// Lo usan el detalle (cuántos agregar) y cada línea del pedido.
@Composable
fun QuantityStepper(
    quantity: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(AppSurface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onDecrease, modifier = Modifier.size(36.dp)) {
            Icon(Icons.Outlined.Remove, contentDescription = stringResource(R.string.quantity_less))
        }
        Text(
            quantity.toString(),
            color = AppPrimary,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        IconButton(onClick = onIncrease, modifier = Modifier.size(36.dp)) {
            Icon(Icons.Outlined.Add, contentDescription = stringResource(R.string.quantity_more))
        }
    }
}
