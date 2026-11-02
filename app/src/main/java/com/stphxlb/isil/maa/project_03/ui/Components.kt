package com.stphxlb.isil.maa.project_03.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.stphxlb.isil.maa.project_03.ui.theme.AppBackground
import com.stphxlb.isil.maa.project_03.ui.theme.AppForeground
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSecondary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface

// El botón de la plantilla es una pastilla ámbar.
// RoundedCornerShape(50) deja las puntas redondas. El color sale de Color.kt.
@Composable
fun EmberButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppPrimary,
            contentColor = AppBackground,
            disabledContainerColor = AppSurface,
            disabledContentColor = AppSecondary
        )
    ) {
        Text(text)
    }
}

// Un chip de categoría o de tamaño.
// Si está elegido, fondo ámbar y texto oscuro. Si no, fondo de tarjeta.
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
