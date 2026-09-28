package com.kafecito.app.util

import android.util.Patterns

/** Validaciones de formularios (requisito 4 del Avance 1). Usadas en Login y Registro. */
object Validaciones {

    const val LARGO_MINIMO_PASSWORD = 6

    fun esCorreoValido(correo: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(correo).matches()

    fun esPasswordValida(password: String): Boolean =
        password.length >= LARGO_MINIMO_PASSWORD
}
