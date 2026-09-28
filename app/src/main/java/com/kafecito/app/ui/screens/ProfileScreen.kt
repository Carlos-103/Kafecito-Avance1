package com.kafecito.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kafecito.app.data.repository.KafecitoRepository
import com.kafecito.app.ui.theme.*

/** Perfil. YA FUNCIONA: muestra nombre y correo del usuario y el botón "Cerrar sesión". */
@Composable
fun ProfileScreen(onCerrarSesion: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KafeWhite)
            .padding(20.dp)
    ) {
        Text(text = "PERFIL", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(KafeChip),
                contentAlignment = Alignment.Center
            ) { Icon(Icons.Filled.Person, contentDescription = null) }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = KafecitoRepository.nombreUsuarioActual(), style = MaterialTheme.typography.bodyLarge)
                Text(
                    text = KafecitoRepository.correoUsuarioActual(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = KafeGray
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onCerrarSesion,
            colors = ButtonDefaults.buttonColors(containerColor = KafeBlack, contentColor = KafeWhite),
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) { Text("Cerrar sesión") }
    }
}
