package com.kafecito.app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kafecito.app.data.repository.KafecitoRepository
import com.kafecito.app.util.Validaciones

class LoginViewModel : ViewModel() {

    var correo by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var errorMensaje by mutableStateOf<String?>(null)
        private set

    fun onCorreoChange(value: String) { correo = value }
    fun onPasswordChange(value: String) { password = value }

    /** Valida el formulario y devuelve true si el login fue exitoso. */
    fun intentarLogin(): Boolean {
        if (correo.isBlank() || password.isBlank()) {
            errorMensaje = "Correo y contraseña son obligatorios"
            return false
        }
        if (!Validaciones.esCorreoValido(correo.trim())) {
            errorMensaje = "El correo no tiene un formato válido"
            return false
        }
        val usuario = KafecitoRepository.login(correo.trim(), password)
        return if (usuario != null) {
            errorMensaje = null
            true
        } else {
            errorMensaje = "Correo o contraseña incorrectos"
            false
        }
    }
}
