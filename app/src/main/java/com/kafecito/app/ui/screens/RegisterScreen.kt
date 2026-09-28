package com.kafecito.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kafecito.app.data.repository.KafecitoRepository
import com.kafecito.app.ui.components.KafeDarkField
import com.kafecito.app.ui.theme.KafeBlack
import com.kafecito.app.ui.theme.KafeGray
import com.kafecito.app.ui.theme.KafeWhite
import com.kafecito.app.util.Validaciones

/** Pantalla "Crear cuenta". YA FUNCIONA: valida y guarda el usuario en KafecitoRepository. */
@Composable
fun RegisterScreen(
    onCuentaCreada: () -> Unit,
    onAtras: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmar by remember { mutableStateOf("") }
    var aceptaTerminos by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KafeBlack)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        IconButton(onClick = onAtras) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = KafeWhite)
        }

        Text(text = "KAFECITO", color = KafeGray, style = MaterialTheme.typography.bodyMedium)
        Text(text = "Crear cuenta", color = KafeWhite, style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(24.dp))

        KafeDarkField(label = "Nombre completo", value = nombre, onChange = { nombre = it })
        Spacer(modifier = Modifier.height(12.dp))
        KafeDarkField(label = "Correo", value = correo, onChange = { correo = it }, keyboardType = KeyboardType.Email)
        Spacer(modifier = Modifier.height(12.dp))
        KafeDarkField(label = "Contraseña", value = password, onChange = { password = it }, esPassword = true)
        Spacer(modifier = Modifier.height(12.dp))
        KafeDarkField(label = "Confirmar contraseña", value = confirmar, onChange = { confirmar = it }, esPassword = true)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            Checkbox(
                checked = aceptaTerminos,
                onCheckedChange = { aceptaTerminos = it },
                colors = CheckboxDefaults.colors(checkedColor = KafeWhite, uncheckedColor = KafeGray, checkmarkColor = KafeBlack)
            )
            Text("Acepto términos y condiciones", color = KafeGray)
        }

        error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                error = validarRegistro(nombre, correo, password, confirmar, aceptaTerminos)
                    // Si las validaciones pasan, se intenta crear la cuenta.
                    ?: KafecitoRepository.registrar(nombre.trim(), correo.trim(), password)
                if (error == null) onCuentaCreada()
            },
            colors = ButtonDefaults.buttonColors(containerColor = KafeWhite, contentColor = KafeBlack),
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) { Text("CREAR CUENTA") }
    }
}

/** Devuelve null si todo está bien, o el mensaje del primer error encontrado. */
private fun validarRegistro(
    nombre: String,
    correo: String,
    password: String,
    confirmar: String,
    aceptaTerminos: Boolean
): String? {
    if (nombre.isBlank() || correo.isBlank() || password.isBlank() || confirmar.isBlank()) {
        return "Todos los campos son obligatorios"
    }
    if (!Validaciones.esCorreoValido(correo.trim())) return "El correo no tiene un formato válido"
    if (!Validaciones.esPasswordValida(password)) {
        return "La contraseña debe tener al menos ${Validaciones.LARGO_MINIMO_PASSWORD} caracteres"
    }
    if (password != confirmar) return "Las contraseñas no coinciden"
    if (!aceptaTerminos) return "Debes aceptar los términos y condiciones"
    return null
}
