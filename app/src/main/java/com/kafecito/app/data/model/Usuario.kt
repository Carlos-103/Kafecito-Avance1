package com.kafecito.app.data.model
//Representa a un usuario con sus datos persosnale
enum class Rol { ADMIN, CLIENTE }

data class Usuario(
    val id: Int,
    val nombre: String,
    val correo: String,
    val password: String
)
