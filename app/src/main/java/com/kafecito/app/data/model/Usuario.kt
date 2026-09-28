package com.kafecito.app.data.model

enum class Rol { ADMIN, EMPLEADO, CLIENTE }

// Si el Usuario de la Etapa 2 tenía más campos (teléfono, dirección, etc.),
// agregarlos aquí.
data class Usuario(
    val id: Int,
    val nombre: String,
    val correo: String,
    val password: String,
    val rol: Rol
)
