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
    // Parte del CARRITO (completada)
    // ========================================================================

    /** Cambia la cantidad de un producto ya agregado, respetando el stock. */
    fun cambiarCantidad(productoId: Int, nuevaCantidad: Int) {
        val indice = _items.indexOfFirst { it.producto.id == productoId }
        if (indice < 0) return
        val item = _items[indice]
        when {
            nuevaCantidad <= 0 -> quitar(productoId)
            nuevaCantidad > item.producto.stock -> Unit // no se permite pasar del stock
            else -> _items[indice] = item.copy(cantidad = nuevaCantidad)
        }
    }

    /** Quita un producto del carrito. */
    fun quitar(productoId: Int) {
        _items.removeAll { it.producto.id == productoId }
    }

    /** Vacía el carrito (se usa al cerrar sesión). */
    fun vaciar() {
        _items.clear()
    }

    /** Suma de todas las líneas, sin IVA. */
    val subtotal: Double get() = _items.sumOf { it.totalLinea }

    /** IVA = 13 % del subtotal. */
    val iva: Double get() = subtotal * TASA_IVA

    /** Total a pagar = subtotal + IVA. */
    val total: Double get() = subtotal + iva

    private companion object {
        const val TASA_IVA = 0.13
    }
}