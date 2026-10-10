package com.kafecito.app.data.model
import androidx.annotation.DrawableRes


data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int,
    val categoria: String,
    @DrawableRes val imagen: Int? = null
)
