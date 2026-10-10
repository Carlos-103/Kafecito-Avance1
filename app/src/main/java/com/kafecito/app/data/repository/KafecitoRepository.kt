package com.kafecito.app.data.repository

import com.kafecito.app.data.model.Producto
import com.kafecito.app.data.model.Rol
import com.kafecito.app.data.model.Usuario

/** Repositorio en memoria (los datos se pierden al cerrar la app). */
object KafecitoRepository {

    // ------------------------------------------------------------------------
    // USUARIOS  (registro y login ya funcionan en memoria)
    // ------------------------------------------------------------------------

    private val usuarios = mutableListOf(
        Usuario(1, "David", "admin@kafecito.com", "123456", Rol.ADMIN)
    )
    private var usuarioActual: Usuario? = null

    /** Crea la cuenta. Devuelve null si salió bien, o el mensaje de error. */
    fun registrar(nombre: String, correo: String, password: String): String? {
        if (usuarios.any { it.correo.equals(correo, ignoreCase = true) }) {
            return "Ya existe una cuenta con ese correo"
        }
        val nuevo = Usuario(usuarios.size + 1, nombre, correo, password, Rol.CLIENTE)
        usuarios.add(nuevo)
        usuarioActual = nuevo
        return null
    }

    /** Devuelve el usuario si las credenciales son correctas, o null. */
    fun login(correo: String, password: String): Usuario? {
        val usuario = usuarios.find {
            it.correo.equals(correo, ignoreCase = true) && it.password == password
        }
        usuarioActual = usuario
        return usuario
    }

    fun cerrarSesion() { usuarioActual = null }

    fun nombreUsuarioActual(): String = usuarioActual?.nombre ?: "Invitado"

    fun correoUsuarioActual(): String = usuarioActual?.correo ?: ""

    // ------------------------------------------------------------------------
    // CATÁLOGO
    // ------------------------------------------------------------------------

    // TODO(CATALOGO): CATEGORÍAS REALES
    //   Estas categorías salen de los chips de la pantalla Home. Si en la Etapa 2
    //   las categorías eran otras, cambiar esta lista (el Home se actualiza solo).
    val categorias = listOf("Café", "Desayuno", "Postres", "Bebidas")

    // TODO(CATALOGO): PRODUCTOS REALES DE LA ETAPA 2
    //   Qué hacer:
    //   1. Abrir Producto.kt e Inventario.kt de la Etapa 2 (proyecto de consola).
    //   2. Reemplazar estos productos de ejemplo por los del proyecto real:
    //      mismo id, nombre, descripción, precio, stock y categoría.
    //   3. La categoría de cada producto debe coincidir EXACTAMENTE con un valor
    //      de la lista "categorias" de arriba (si no, no aparece en ningún chip).
    //   4. Cuando se tengan las fotos, llenar el último parámetro imagenUrl
    //      (ver TODO(IMAGENES)).
    private val productos = listOf(
        Producto(1, "Capuccino", "Espresso con leche vaporizada y espuma", 2.50, 4, "Café"),
        Producto(2, "Café Americano", "Espresso diluido en agua caliente", 2.00, 8, "Café"),
        Producto(3, "Pastel de Chocolate", "Pastel suave de chocolate", 3.00, 2, "Postres"),
        Producto(4, "Sprite", "Gaseosa", 1.00, 5, "Bebidas"),
        Producto(5, "Desayuno tipico", "Frijoles molidos, huevos revueltos y platanos fritos", 1.00, 5, "Desayuno")

    )

    fun obtenerProductos(categoria: String? = null): List<Producto> =
        if (categoria == null) productos else productos.filter { it.categoria == categoria }

    fun obtenerProducto(id: Int): Producto? = productos.find { it.id == id }
}
