package com.stphxlb.isil.maa.project_03.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.stphxlb.isil.maa.project_03.R
import com.stphxlb.isil.maa.project_03.ui.theme.AppBackground
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppTheme

// La barra de arriba, la misma en las 4 pantallas.
// A la izquierda: la flecha si la pantalla tiene a dónde volver, si no la taza de Haze.
// A la derecha: el carrito con el número de cafés del pedido.
// No sabe de Activities: solo avisa con onBackClick y onCartClick, y la pantalla decide.
@Composable
fun HazeTopBar(
    title: String,
    cartCount: Int,
    onCartClick: () -> Unit,
    onBackClick: (() -> Unit)? = null // null = sin flecha
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = AppTheme.spacing.small, vertical = AppTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBackClick != null) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        } else {
            Image(
                painter = painterResource(R.drawable.haze_logo),
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier
                    .padding(horizontal = AppTheme.spacing.small)
                    .size(36.dp)
            )
        }

        Text(
            title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .weight(1f) // empuja el carrito al borde derecho
                .padding(start = AppTheme.spacing.small)
        )

        IconButton(onClick = onCartClick) {
            // el globo solo sale si hay algo en el pedido
            BadgedBox(
                badge = {
                    if (cartCount > 0) {
                        Badge(containerColor = AppPrimary, contentColor = AppBackground) {
                            Text(cartCount.toString())
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = stringResource(R.string.cart_open)
                )
            }
        }
    }
}
