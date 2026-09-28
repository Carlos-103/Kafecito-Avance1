package com.kafecito.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kafecito.app.data.model.ItemCarrito
import com.kafecito.app.ui.theme.*
import com.kafecito.app.viewmodel.CarritoViewModel

/**
 * "Mi pedido". Ya muestra los productos agregados desde el Home o el Detalle.
 * Falta lo marcado con TODO(CARRITO): los totales (que dependen de CarritoViewModel),
 * y los botones para cambiar la cantidad o quitar productos.
 */
@Composable
fun CartScreen(
    carritoVM: CarritoViewModel,
    onAtras: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KafeBlack)
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onAtras) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás", tint = KafeWhite)
            }
            Text(text = "MI PEDIDO", color = KafeWhite, style = MaterialTheme.typography.titleMedium)
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (carritoVM.items.isEmpty()) {
            Text(text = "Tu pedido está vacío", color = KafeGray, modifier = Modifier.weight(1f))
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(carritoVM.items) { item -> CartItem(item) }
            }
        }

        // TODO(CARRITO): estas tres filas muestran hoy $0.00 porque subtotal, iva y total
        //   están sin implementar en viewmodel/CarritoViewModel.kt. Al completarlas allá,
        //   aquí se actualizan solas (no hay que cambiar nada en estas filas).
        TotalRow("Subtotal", carritoVM.subtotal)
        TotalRow("IVA (13%)", carritoVM.iva)
        Spacer(modifier = Modifier.height(4.dp))
        TotalRow("TOTAL", carritoVM.total, destacado = true)
    }
}

@Composable
private fun CartItem(item: ItemCarrito) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(KafeCard)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // TODO(IMAGENES): mostrar aquí la miniatura del producto a la izquierda del nombre
        //   (AsyncImage de 48.dp x 48.dp, igual que en el Figma).
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.producto.nombre, color = KafeWhite, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Cantidad: ${item.cantidad}",
                color = KafeGray,
                style = MaterialTheme.typography.bodyMedium
            )
            // TODO(CARRITO): agregar aquí los botones "-" y "+" para cambiar la cantidad
            //   (llaman a carritoVM.cambiarCantidad) y un botón para quitar el producto
            //   (llama a carritoVM.quitar). Hay que pasar carritoVM a este composable
            //   o recibir funciones lambda (onCambiarCantidad, onQuitar) desde CartScreen.
        }
        Text(
            text = "$${"%.2f".format(item.totalLinea)}",
            color = KafeWhite,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun TotalRow(etiqueta: String, monto: Double, destacado: Boolean = false) {
    val peso = if (destacado) FontWeight.Bold else FontWeight.Normal
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = etiqueta, color = KafeWhite, fontWeight = peso)
        Text(text = "$${"%.2f".format(monto)}", color = KafeWhite, fontWeight = peso)
    }
}
