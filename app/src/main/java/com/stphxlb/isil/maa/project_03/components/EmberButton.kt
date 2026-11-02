package com.stphxlb.isil.maa.project_03.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stphxlb.isil.maa.project_03.ui.theme.AppBackground
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSecondary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface

// El botón de la plantilla es una pastilla ámbar.
// RoundedCornerShape(50) deja las puntas redondas. El color sale de Color.kt.
// Lo usan la portada (Empezar), el detalle (Agregar) y el pedido (Hacer pedido).
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
