package com.stphxlb.isil.maa.project_03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.stphxlb.isil.maa.project_03.components.ChoiceChip
import com.stphxlb.isil.maa.project_03.components.EmberButton
import com.stphxlb.isil.maa.project_03.components.HazeDestination
import com.stphxlb.isil.maa.project_03.components.HazeScaffold
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppTheme
import com.stphxlb.isil.maa.project_03.ui.theme.Project_03_Theme
import com.stphxlb.isil.maa.project_03.ui.theme.edgeToEdgeDark

// Tu pedido, la tercera pantalla de la plantilla. En la barra de abajo es Pedido.
// Delivery o Recojo solo cambia el chip. No hay costo de envío en la base.
// Hacer pedido vacía el carrito y deja un texto. No llama a ningún PHP.
class OrderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edgeToEdgeDark()
        setContent {
            Project_03_Theme {
                OrderScreen(onNavigate = { destination -> navigateTo(destination) })
            }
        }
    }
}

@Composable
fun OrderScreen(onNavigate: (HazeDestination) -> Unit) {
    var delivery by remember { mutableStateOf(true) }
    var placed by remember { mutableStateOf(false) }
    val total = Cart.lines.sumOf { it.lineTotal() }

    HazeScaffold(
        title = stringResource(R.string.order_title),
        selected = HazeDestination.ORDER,
        cartCount = Cart.count(),
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
            if (placed && Cart.lines.isEmpty()) {
                Text(stringResource(R.string.order_done))
            } else if (Cart.lines.isEmpty()) {
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

                Cart.lines.forEach { line ->
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

                EmberButton(
                    text = stringResource(R.string.order_place),
                    onClick = {
                        Cart.clear()
                        placed = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = AppTheme.spacing.large)
                )
            }
        }
    }
}
