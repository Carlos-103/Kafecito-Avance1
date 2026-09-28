package com.kafecito.app.ui.theme

import androidx.compose.ui.graphics.Color

// ============================================================================
// TODO(TEMA): COLORES SEGÚN EL FIGMA
//   Qué hacer:
//   1. Abrir el Figma, seleccionar un elemento y copiar el código HEX del "Fill"
//      (panel derecho).
//   2. Reemplazar aquí el valor correspondiente. Un HEX #1A1A1A se escribe así:
//      Color(0xFF1A1A1A)   (el "FF" del inicio significa sin transparencia).
//   3. NO cambiar los nombres de las variables (KafeBlack, KafeWhite, ...):
//      todas las pantallas usan estos nombres, así que al cambiar el valor aquí
//      se actualiza toda la app.
//   4. Si el Figma usa un color que no está en esta lista, agregar una variable
//      nueva y usarla en la pantalla que la necesite.
// ============================================================================

val KafeBlack = Color(0xFF141414)             // Fondo oscuro (Welcome, Login, Registro, Carrito, Detalle) y botones principales
val KafeWhite = Color(0xFFFFFFFF)             // Fondo claro (Home, Perfil, Contacto) y texto sobre fondo oscuro
val KafeCard = Color(0xFF262626)              // Tarjetas sobre fondo oscuro (items del carrito)
val KafeSurface = Color(0xFFF7F7F7)           // Tarjetas sobre fondo claro (productos)
val KafeChip = Color(0xFFF0F0F0)              // Chips de categoría no seleccionados
val KafeImagePlaceholder = Color(0xFFD9D9D9)  // Cuadro gris temporal mientras no hay fotos (ver TODO(IMAGENES))
val KafeGray = Color(0xFF9E9E9E)              // Texto secundario / descripciones
val KafeBorderDark = Color(0xFF555555)        // Borde de los campos de texto sobre fondo oscuro
val ErrorRed = Color(0xFFE0483E)              // Mensajes de error
