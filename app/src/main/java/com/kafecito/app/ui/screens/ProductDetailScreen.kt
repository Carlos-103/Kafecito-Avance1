package com.kafecito.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kafecito.app.data.model.Producto
import com.kafecito.app.ui.theme.*

/**
 * Detalle de producto (pantalla del Figma con la foto grande y "Agregar pedido").
 * Se abre al tocar una tarjeta del Home.
 *
 * Ya funciona: muestra los datos y el botón agrega el producto al carrito y regresa.
 * Falta lo marcado con TODO(DETALLE) y TODO(IMAGENES).
 */
@Composable
fun ProductDetailScreen(
    producto: Producto?,
    onAtras: () -> Unit,
    onAgregar: (Producto, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KafeBlack)
            .padding(20.dp)
    ) {
        IconButton(onClick = onAtras) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = KafeWhite)
        }

        if (producto == null) {
            Text("Producto no encontrado", color = KafeWhite)
            return@Column
        }

        // TODO(DETALLE): la cantidad hoy queda fija en 1. Agregar el selector de cantidad
        //   del Figma (botones "-" y "+" con el número en medio):
        //     - "-" resta 1, pero nunca baja de 1.
        //     - "+" suma 1, pero nunca supera producto.stock.
        //   Mostrar el selector en el espacio marcado más abajo ("Cantidad").
        var cantidad by remember { mutableStateOf(1) }

        // TODO(IMAGENES): reemplazar este Box gris por la foto grande del producto
        //   (mismo AsyncImage explicado en HomeScreen.kt, con altura de 220.dp).
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(KafeImagePlaceholder)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = producto.nombre, color = KafeWhite, style = MaterialTheme.typography.titleMedium)
            Text(text = "$${"%.2f".format(producto.precio)}", color = KafeWhite, style = MaterialTheme.typography.titleMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = producto.descripcion, color = KafeGray, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(20.dp))

        // TODO(DETALLE): aquí va el selector de cantidad (ver nota arriba).
        Text(text = "Cantidad: $cantidad", color = KafeWhite, style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.weight(1f))

        // TODO(DETALLE): el texto del botón debe mostrar el total según la cantidad,
        //   como en el Figma: "Agregar pedido $7.00" (precio * cantidad, con 2 decimales).
        //   Pista: "%.2f".format(producto.precio * cantidad)
        Button(
            onClick = {
                onAgregar(producto, cantidad)
                onAtras()
            },
            colors = ButtonDefaults.buttonColors(containerColor = KafeWhite, contentColor = KafeBlack),
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) { Text("Agregar pedido") }
    }
}
