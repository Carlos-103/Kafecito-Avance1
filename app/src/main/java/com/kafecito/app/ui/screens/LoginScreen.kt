package com.kafecito.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kafecito.app.ui.components.KafeDarkField
import com.kafecito.app.ui.theme.KafeBlack
import com.kafecito.app.ui.theme.KafeGray
import com.kafecito.app.ui.theme.KafeWhite
import com.kafecito.app.viewmodel.LoginViewModel

/**
 * Pantalla "Bienvenido de nuevo". YA FUNCIONA con validaciones.
 * Usuario de prueba: admin@kafecito.com / 123456 (o cualquier cuenta creada en Registro).
 */
@Composable
fun LoginScreen(
    onLoginExitoso: () -> Unit,
    onAtras: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KafeBlack)
            .padding(24.dp)
    ) {
        IconButton(onClick = onAtras) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = KafeWhite)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "KAFECITO", color = KafeGray, style = MaterialTheme.typography.bodyMedium)
        Text(text = "Bienvenido de nuevo", color = KafeWhite, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(32.dp))

        KafeDarkField(
            label = "Correo electrónico",
            value = viewModel.correo,
            onChange = viewModel::onCorreoChange,
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(16.dp))
        KafeDarkField(
            label = "Contraseña",
            value = viewModel.password,
            onChange = viewModel::onPasswordChange,
            keyboardType = KeyboardType.Password,
            esPassword = true
        )

        viewModel.errorMensaje?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { if (viewModel.intentarLogin()) onLoginExitoso() },
            colors = ButtonDefaults.buttonColors(containerColor = KafeWhite, contentColor = KafeBlack),
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) { Text("INICIAR SESIÓN") }
    }
}
