package com.stphxlb.isil.maa.project_03.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import com.stphxlb.isil.maa.project_03.ui.theme.AppBackground
import com.stphxlb.isil.maa.project_03.ui.theme.AppSecondary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface
import com.stphxlb.isil.maa.project_03.ui.theme.EmberGradient

// El botón de la plantilla: una pastilla con el degradado ámbar.
// Button no acepta un degradado como color, así que lo dejo transparente
// y pinto el degradado detrás con background, recortado con la misma forma.
// Lo usan la portada (Empezar), el detalle (Agregar), el pedido (Hacer pedido) y el aviso.
@Composable
fun EmberButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val shape = RoundedCornerShape(50) // puntas redondas
    val brush = if (enabled) EmberGradient else SolidColor(AppSurface)

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.background(brush, shape),
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = AppBackground,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = AppSecondary
        )
    ) {
        Text(text)
    }
}
