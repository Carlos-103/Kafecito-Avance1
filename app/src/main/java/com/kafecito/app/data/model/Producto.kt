package com.kafecito.app.data.model

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int,
    val categoria: String,
    // TODO(IMAGENES): aquí va la URL de la foto (https://...) de cada producto.
    //   Se llena en data/repository/KafecitoRepository.kt. Dejar null si se usan
    //   imágenes locales (carpeta res/drawable).
    val imagenUrl: String? = null
)
