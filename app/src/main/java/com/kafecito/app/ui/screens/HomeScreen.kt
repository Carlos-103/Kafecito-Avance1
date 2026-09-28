package com.kafecito.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.kafecito.app.data.model.Producto
import com.kafecito.app.data.repository.KafecitoRepository
import com.kafecito.app.ui.theme.*

/**
 * Pantalla principal: saludo, buscador, categorías y grid de productos.
 * YA FUNCIONA (con productos de ejemplo): filtra por categoría y por texto de búsqueda.
 *
 *  - Tocar una tarjeta abre el detalle del producto.
 *  - Tocar el "+" agrega 1 unidad al carrito (CarritoViewModel.agregar).
 */
@Composable
fun HomeScreen(
    onAbrirCarrito: () -> Unit,
    onAbrirDetalle: (Int) -> Unit,
    onAgregar: (Producto) -> Unit
) {
    var categoriaSeleccionada by remember { mutableStateOf(KafecitoRepository.categorias.first()) }
    var busqueda by remember { mutableStateOf("") }

    val productos = KafecitoRepository.obtenerProductos(categoriaSeleccionada)
        .filter { it.nombre.contains(busqueda, ignoreCase = true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KafeWhite)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¡Hola! ${KafecitoRepository.nombreUsuarioActual()}",
                style = MaterialTheme.typography.titleMedium
            )
            IconButton(onClick = onAbrirCarrito) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            placeholder = { Text("Buscar") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            KafecitoRepository.categorias.forEach { categoria ->
                CategoriaChip(
                    texto = categoria,
                    seleccionada = categoria == categoriaSeleccionada,
                    onClick = { categoriaSeleccionada = categoria }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(productos) { producto ->
                ProductoCard(
                    producto = producto,
                    onClick = { onAbrirDetalle(producto.id) },
                    onAgregar = { onAgregar(producto) }
                )
            }
        }
    }
}

@Composable
private fun CategoriaChip(texto: String, seleccionada: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (seleccionada) KafeBlack else KafeChip)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = texto,
            color = if (seleccionada) KafeWhite else KafeBlack,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ProductoCard(
    producto: Producto,
    onClick: () -> Unit,
    onAgregar: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(KafeSurface)
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        // TODO(IMAGENES): reemplazar este Box gris por la foto real del producto.
        //   1) La URL de cada foto se define en el campo imagenUrl del producto
        //      (data/model/Producto.kt; se llena en data/repository/KafecitoRepository.kt).
        //   2) Usar Coil (la dependencia y el permiso de internet ya están agregados):
        //        AsyncImage(
        //            model = producto.imagenUrl,
        //            contentDescription = producto.nombre,
        //            contentScale = ContentScale.Crop,
        //            modifier = Modifier
        //                .fillMaxWidth()
        //                .height(90.dp)
        //                .clip(RoundedCornerShape(12.dp))
        //        )
        //      Imports necesarios: coil.compose.AsyncImage y androidx.compose.ui.layout.ContentScale
        //   3) Si prefieren imágenes locales: copiarlas a app/src/main/res/drawable/ y usar
        //      Image(painter = painterResource(R.drawable.nombre_imagen), ...)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(KafeImagePlaceholder)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = producto.nombre, style = MaterialTheme.typography.bodyLarge, maxLines = 1)
        Text(
            text = producto.descripcion,
            style = MaterialTheme.typography.bodySmall,
            color = KafeGray,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "$${"%.2f".format(producto.precio)}", style = MaterialTheme.typography.bodyLarge)
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(KafeBlack)
                    .clickable { onAgregar() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Agregar al carrito",
                    tint = KafeWhite,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
