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

/*
 * Pantalla de inicio de sesión.
 * Permite ingresar las credenciales y acceder si son válidas.
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
        // Permite regresar a la pantalla anterior.
        IconButton(onClick = onAtras) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = KafeWhite)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "KAFECITO", color = KafeGray, style = MaterialTheme.typography.bodyMedium)
        Text(text = "Bienvenido de nuevo", color = KafeWhite, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(32.dp))
// Los campos se conectan al ViewModel para mantener los datos ingresados.
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
        // El mensaje de error aparece únicamente cuando existe uno.
        viewModel.errorMensaje?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            // Si las credenciales son válidas, se notifica el inicio de sesión exitoso.
            onClick = { if (viewModel.intentarLogin()) onLoginExitoso() },
            colors = ButtonDefaults.buttonColors(containerColor = KafeWhite, contentColor = KafeBlack),
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) { Text("INICIAR SESIÓN") }
    }
}
