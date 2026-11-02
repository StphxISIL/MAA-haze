package com.stphxlb.isil.maa.project_03.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

// El marco de todas las pantallas: barra arriba, barra abajo y el contenido al medio.
// Las 4 Activities lo usan, así que si cambio una barra cambia en toda la app.
// content recibe el padding de las barras para que nada quede tapado.
@Composable
fun HazeScaffold(
    title: String,
    selected: HazeDestination,
    cartCount: Int,
    onNavigate: (HazeDestination) -> Unit,
    onBackClick: (() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            HazeTopBar(
                title = title,
                cartCount = cartCount,
                onCartClick = { onNavigate(HazeDestination.ORDER) },
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            HazeBottomBar(selected = selected, onNavigate = onNavigate)
        },
        content = content
    )
}
