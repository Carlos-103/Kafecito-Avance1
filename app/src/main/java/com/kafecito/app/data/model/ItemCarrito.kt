package com.kafecito.app.data.model

/** Una línea del carrito: un producto y cuántas unidades se piden. */
data class ItemCarrito(
    val producto: Producto,
    val cantidad: Int
) {
    val totalLinea: Double get() = producto.precio * cantidad
}
