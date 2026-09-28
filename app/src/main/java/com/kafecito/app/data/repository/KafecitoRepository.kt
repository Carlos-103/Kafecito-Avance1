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
        Producto(1, "Espresso con leche", "Espresso con leche vaporizada y una capa espesa de espuma", 3.50, 40, "Café"),
        Producto(2, "Cappuccino", "Espresso con leche vaporizada y una capa espesa de espuma", 3.50, 35, "Café"),
        Producto(3, "Americano", "Espresso diluido en agua caliente", 2.50, 50, "Café"),
        Producto(4, "Latte", "Espresso con abundante leche vaporizada", 3.25, 30, "Café"),
        Producto(5, "Hojaldre francés", "Hojaldre recién horneado, mantequilla pura", 6.50, 15, "Desayuno"),
        Producto(6, "Tostada con huevo", "Pan artesanal con huevo y vegetales", 5.75, 12, "Desayuno"),
        Producto(7, "Tarta de queso", "Tarta cremosa de queso sobre base de galleta", 4.50, 10, "Postres"),
        Producto(8, "Brownie", "Brownie de chocolate con nuez", 3.75, 20, "Postres"),
        Producto(9, "Frappé de café", "Bebida fría batida con café y hielo", 4.00, 18, "Bebidas"),
        Producto(10, "Té helado", "Té negro servido frío con limón", 2.75, 25, "Bebidas")
    )

    fun obtenerProductos(categoria: String? = null): List<Producto> =
        if (categoria == null) productos else productos.filter { it.categoria == categoria }

    fun obtenerProducto(id: Int): Producto? = productos.find { it.id == id }
}
