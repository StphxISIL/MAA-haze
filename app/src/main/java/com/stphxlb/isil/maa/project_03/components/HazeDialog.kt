package com.stphxlb.isil.maa.project_03.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.stphxlb.isil.maa.project_03.ui.theme.AppForeground
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSecondary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface

// El aviso de la app, con los colores de Ember en vez del blanco de Material.
// Confirmar es la pastilla ámbar (EmberButton). Cancelar es solo texto.
// La pantalla decide cuándo se ve: lo pone dentro de un if con su propio estado.
@Composable
fun HazeDialog(
    title: String,
    message: String,
    confirmText: String,
    dismissText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss, // tocar fuera del aviso es lo mismo que Cancelar
        shape = RoundedCornerShape(24.dp),
        containerColor = AppSurface,
        titleContentColor = AppForeground,
        textContentColor = AppSecondary,
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            EmberButton(text = confirmText, onClick = onConfirm)
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(dismissText, color = AppPrimary)
            }
        }
    )
}
