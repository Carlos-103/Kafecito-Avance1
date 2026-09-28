package com.kafecito.app.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.kafecito.app.data.model.ItemCarrito
import com.kafecito.app.data.model.Producto

/**
 * Estado del carrito, compartido por Home (botón "+"), Detalle ("Agregar pedido")
 * y Carrito. Se crea una sola vez en navigation/NavGraph.kt.
 */
class CarritoViewModel : ViewModel() {

    private val _items = mutableStateListOf<ItemCarrito>()
    val items: List<ItemCarrito> get() = _items

    /** YA FUNCIONA: agrega el producto, o suma la cantidad si ya estaba en el carrito. */
    fun agregar(producto: Producto, cantidad: Int = 1) {
        val indice = _items.indexOfFirst { it.producto.id == producto.id }
        if (indice >= 0) {
            val actual = _items[indice]
            _items[indice] = actual.copy(cantidad = actual.cantidad + cantidad)
        } else {
            _items.add(ItemCarrito(producto, cantidad))
        }
    }

    // ========================================================================
    // TODO(CARRITO): completar las funciones y cálculos de abajo.
    //   Todo se hace en este archivo y en ui/screens/CartScreen.kt.
    // ========================================================================

    // TODO(CARRITO): cambiar la cantidad de un producto ya agregado.
    //   - Buscar el ítem con _items.indexOfFirst { it.producto.id == productoId }
    //   - Si nuevaCantidad <= 0 -> quitarlo (llamar a quitar()).
    //   - Si nuevaCantidad supera producto.stock -> no permitirlo (dejar la cantidad como estaba).
    //   - Si no, reemplazarlo: _items[indice] = item.copy(cantidad = nuevaCantidad)
    fun cambiarCantidad(productoId: Int, nuevaCantidad: Int) {
    }

    // TODO(CARRITO): quitar un producto del carrito.
    //   Pista: _items.removeAll { it.producto.id == productoId }
    fun quitar(productoId: Int) {
    }

    // TODO(CARRITO): vaciar el carrito (se usa al cerrar sesión).
    //   Pista: _items.clear()
    fun vaciar() {
    }

    // TODO(CARRITO): subtotal = suma de totalLinea de todos los ítems (sin IVA).
    //   Pista: _items.sumOf { it.totalLinea }
    val subtotal: Double get() = 0.0

    // TODO(CARRITO): IVA = 13 % del subtotal.
    val iva: Double get() = 0.0

    // TODO(CARRITO): total = subtotal + iva.
    val total: Double get() = 0.0
}
