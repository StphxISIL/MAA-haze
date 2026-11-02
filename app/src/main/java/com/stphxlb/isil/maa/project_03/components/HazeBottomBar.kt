package com.stphxlb.isil.maa.project_03.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocalCafe
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.stphxlb.isil.maa.project_03.R
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSecondary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSurface
import com.stphxlb.isil.maa.project_03.ui.theme.AppTheme

// Los tres lugares a los que se llega desde la barra de abajo.
// El detalle no está: a ese se entra tocando un café, y marca Carta.
enum class HazeDestination { HOME, MENU, ORDER }

// La barra de abajo, la misma en las 4 pantallas. Antes era HomeBar, solo en la home.
// selected pinta de ámbar dónde estoy. onNavigate avisa a dónde quiere ir el usuario.
@Composable
fun HazeBottomBar(
    selected: HazeDestination,
    onNavigate: (HazeDestination) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppSurface)
            .navigationBarsPadding()
            .padding(vertical = AppTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        HazeBarItem(
            icon = Icons.Outlined.Home,
            label = stringResource(R.string.nav_home),
            selected = selected == HazeDestination.HOME,
            onClick = { onNavigate(HazeDestination.HOME) }
        )
        HazeBarItem(
            icon = Icons.Outlined.LocalCafe,
            label = stringResource(R.string.nav_menu),
            selected = selected == HazeDestination.MENU,
            onClick = { onNavigate(HazeDestination.MENU) }
        )
        HazeBarItem(
            icon = Icons.Outlined.ShoppingCart,
            label = stringResource(R.string.nav_order),
            selected = selected == HazeDestination.ORDER,
            onClick = { onNavigate(HazeDestination.ORDER) }
        )
    }
}

// Un botón de la barra: icono arriba, texto abajo.
@Composable
fun HazeBarItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val tint = if (selected) AppPrimary else AppSecondary
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = AppTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = icon, contentDescription = label, tint = tint)
        Text(label, color = tint)
    }
}
