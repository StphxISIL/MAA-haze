package com.stphxlb.isil.maa.project_03.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.stphxlb.isil.maa.project_03.ui.theme.AppBackground
import com.stphxlb.isil.maa.project_03.ui.theme.AppForeground
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface

// Un chip de categoría, de tamaño o de Delivery / Recojo.
// Si está elegido, fondo ámbar y texto oscuro. Si no, fondo de tarjeta.
// El chip no guarda nada: la pantalla le dice si está elegido y él avisa con onClick.
@Composable
fun ChoiceChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val background = if (selected) AppPrimary else AppSurface
    val textColor = if (selected) AppBackground else AppForeground

    Text(
        text = label,
        color = textColor,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(background)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}
