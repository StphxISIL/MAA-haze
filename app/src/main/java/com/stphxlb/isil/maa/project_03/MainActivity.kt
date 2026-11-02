package com.stphxlb.isil.maa.project_03

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.stphxlb.isil.maa.project_03.components.EmberButton
import com.stphxlb.isil.maa.project_03.components.HazeDestination
import com.stphxlb.isil.maa.project_03.components.HazeScaffold
import com.stphxlb.isil.maa.project_03.ui.theme.AppPrimary
import com.stphxlb.isil.maa.project_03.ui.theme.AppSecondary
import com.stphxlb.isil.maa.project_03.ui.theme.AppTheme
import com.stphxlb.isil.maa.project_03.ui.theme.Project_03_Theme
import com.stphxlb.isil.maa.project_03.ui.theme.edgeToEdgeDark

// Portada. Es la que sale al tocar el icono (LAUNCHER en el Manifest).
// Empezar abre la carta. Términos no abre otra Activity: solo muestra el texto acá.
// Es Inicio en la barra de abajo.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // siempre primero, si no la activity no arranca bien
        edgeToEdgeDark()
        setContent {
            Project_03_Theme { // sin esto no agarran mis colores ni DM Sans
                MainScreen(
                    onNavigate = { destination -> navigateTo(destination) }
                )
            }
        }
    }
}

@Composable
fun MainScreen(onNavigate: (HazeDestination) -> Unit) {
    // false al entrar. Al tocar Términos pasa a true y se ve el párrafo, en esta misma pantalla.
    var showTerms by remember { mutableStateOf(false) }

    HazeScaffold(
        title = stringResource(R.string.app_name),
        selected = HazeDestination.HOME,
        cartCount = Cart.count(),
        onNavigate = onNavigate
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()) // si abro términos y no entra, se puede bajar
                .padding(AppTheme.spacing.large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.quote),
                style = MaterialTheme.typography.titleLarge
            )
            Text(stringResource(R.string.author))

            Image(
                painter = painterResource(R.drawable.haze_logo),
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier
                    .padding(top = AppTheme.spacing.extraLarge)
                    .size(AppTheme.sizes.logoSizeLarge)
            )
            Text(
                stringResource(R.string.app_name),
                color = AppPrimary,
                style = MaterialTheme.typography.displayLarge
            )
            EmberButton(
                text = stringResource(R.string.start),
                onClick = { onNavigate(HazeDestination.MENU) }
            )

            Text(
                stringResource(R.string.terms),
                color = AppPrimary,
                modifier = Modifier
                    .padding(top = AppTheme.spacing.medium)
                    .clickable { showTerms = !showTerms }
            )

            if (showTerms) {
                Text(
                    stringResource(R.string.terms_and_conditions),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = AppTheme.spacing.large)
                )
                Text(stringResource(R.string.terms_text))
            }

            Text(
                stringResource(R.string.copyright),
                color = AppSecondary,
                modifier = Modifier.padding(top = AppTheme.spacing.extraLarge)
            )
        }
    }
}
