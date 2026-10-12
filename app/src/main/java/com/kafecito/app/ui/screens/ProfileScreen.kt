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
import com.kafecito.app.ui.components.KafeHeader
import com.kafecito.app.ui.theme.*

/**
 * Pantalla de perfil: muestra el nombre y correo del usuario actual
 * y permite cerrar sesión.
 */
@Composable
fun ProfileScreen(onCerrarSesion: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KafeBlack)
    ) {
        KafeHeader {
            Text(text = "PERFIL", style = MaterialTheme.typography.titleMedium)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Icono que representa al usuario.
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(KafeChip),
                    contentAlignment = Alignment.Center
                ) { Icon(Icons.Filled.Person, contentDescription = null) }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    // Obtiene los datos del usuario actual desde el repositorio.
                    Text(
                        text = KafecitoRepository.nombreUsuarioActual(),
                        color = KafeWhite,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = KafecitoRepository.correoUsuarioActual(),
                        color = KafeGray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            // Ocupa el espacio disponible para colocar el botón al final.
            Spacer(modifier = Modifier.weight(1f))

            // Notifica al componente padre que se debe cerrar la sesión.
            Button(
                onClick = onCerrarSesion,
                colors = ButtonDefaults.buttonColors(containerColor = KafeWhite, contentColor = KafeBlack),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) { Text("Cerrar sesión") }
        }
    }
}
