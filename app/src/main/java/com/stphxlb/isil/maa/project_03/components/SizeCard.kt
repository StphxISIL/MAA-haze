package com.stphxlb.isil.maa.project_03.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stphxlb.isil.maa.project_03.R
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSecondary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface

// Una tarjeta de tamaño del detalle: la letra grande y los ml abajo, como en la plantilla.
// La elegida lleva borde ámbar. No guarda nada: recibe selected y avisa con onClick.
@Composable
fun SizeCard(
    label: String,
    volumeMl: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(16.dp)
    Column(
        modifier = Modifier
            .width(88.dp)
            .clip(shape)
            .background(AppSurface)
            .border(2.dp, if (selected) AppPrimary else Color.Transparent, shape)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            label,
            style = MaterialTheme.typography.titleLarge,
            color = if (selected) AppPrimary else MaterialTheme.colorScheme.onBackground
        )
        Text(stringResource(R.string.size_volume, volumeMl), color = AppSecondary)
    }
}
