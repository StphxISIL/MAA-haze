package com.stphxlb.isil.maa.project_03.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.stphxlb.isil.maa.project_03.R
import com.stphxlb.isil.maa.project_03.components.ChoiceChip
import com.stphxlb.isil.maa.project_03.components.EmberButton
import com.stphxlb.isil.maa.project_03.components.HazeDestination
import com.stphxlb.isil.maa.project_03.components.HazeDialog
import com.stphxlb.isil.maa.project_03.components.HazeScaffold
import com.stphxlb.isil.maa.project_03.data.model.CartLine
import com.stphxlb.isil.maa.project_03.data.model.lineTotal
import com.stphxlb.isil.maa.project_03.data.model.money
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppTheme

// Tu pedido, Pedido en la barra de abajo. Lo dibuja OrderActivity.
// Recibe las líneas del carrito y avisa con onConfirm; no toca el carrito por su cuenta.
// Delivery o Recojo solo cambia el chip. No hay costo de envío en la base.
@Composable
fun OrderScreen(
    lines: List<CartLine>,
    cartCount: Int,
    onNavigate: (HazeDestination) -> Unit,
    onConfirm: () -> Unit
) {
    var delivery by remember { mutableStateOf(true) }
    var placed by remember { mutableStateOf(false) }
    var confirming by remember { mutableStateOf(false) } // true mientras se ve el aviso
    val total = lines.sumOf { it.lineTotal() }

    HazeScaffold(
        title = stringResource(R.string.order_title),
        selected = HazeDestination.ORDER,
        cartCount = cartCount,
        onNavigate = onNavigate
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = AppTheme.spacing.large)
                .padding(bottom = AppTheme.spacing.large)
        ) {
            // placed solo cuenta si el carrito sigue vacío: si vuelvo y agrego otro café, se ve el pedido nuevo
            if (placed && lines.isEmpty()) {
                Text(stringResource(R.string.order_done))
            } else if (lines.isEmpty()) {
                Text(stringResource(R.string.order_empty))
            } else {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.spacing.small)
                ) {
                    ChoiceChip(
                        label = stringResource(R.string.order_delivery),
                        selected = delivery,
                        onClick = { delivery = true }
                    )
                    ChoiceChip(
                        label = stringResource(R.string.order_pickup),
                        selected = !delivery,
                        onClick = { delivery = false }
                    )
                }

                lines.forEach { line ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = AppTheme.spacing.medium),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(line.name, style = MaterialTheme.typography.titleLarge)
                            Text(line.size + "  x" + line.quantity)
                        }
                        Text(money(line.lineTotal()), color = AppPrimary)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = AppTheme.spacing.large),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        stringResource(R.string.order_total),
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(money(total), color = AppPrimary, style = MaterialTheme.typography.titleLarge)
                }

                // Hacer pedido ya no vacía el carrito de una: primero pregunta.
                EmberButton(
                    text = stringResource(R.string.order_place),
                    onClick = { confirming = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = AppTheme.spacing.large)
                )
            }

            if (confirming) {
                HazeDialog(
                    title = stringResource(R.string.order_confirm_title),
                    message = stringResource(R.string.order_confirm_text, money(total)),
                    confirmText = stringResource(R.string.order_confirm),
                    dismissText = stringResource(R.string.order_cancel),
                    onConfirm = {
                        onConfirm()
                        placed = true
                        confirming = false
                    },
                    onDismiss = { confirming = false }
                )
            }
        }
    }
}
