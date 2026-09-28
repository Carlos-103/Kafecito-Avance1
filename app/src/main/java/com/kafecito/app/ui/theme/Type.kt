package com.kafecito.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ============================================================================
// TODO(TEMA): TIPOGRAFÍA SEGÚN EL FIGMA
//   Qué hacer:
//   1. En el Figma, seleccionar un texto y ver en el panel derecho: fuente,
//      tamaño (size) y grosor (weight).
//   2. Ajustar aquí fontSize / fontWeight de cada estilo para que coincida.
//      Dónde se usa cada estilo:
//        titleLarge  -> títulos grandes ("Bienvenido de nuevo", "Crear cuenta", KAFECITO)
//        titleMedium -> encabezados de pantalla ("¡Hola! David", "PERFIL", "MI PEDIDO")
//        bodyLarge   -> nombres de producto y texto normal
//        bodyMedium  -> descripciones, etiquetas
//        bodySmall   -> texto pequeño (descripción dentro de las tarjetas)
//   3. Si el Figma usa una fuente distinta a la del sistema:
//        a) Descargar el .ttf y copiarlo a app/src/main/res/font/ (crear la carpeta
//           "font" si no existe; nombre en minúsculas, ej. poppins_regular.ttf).
//        b) Crear: val KafeFont = FontFamily(Font(R.font.poppins_regular))
//        c) Agregar fontFamily = KafeFont a cada TextStyle de abajo.
// ============================================================================

val Typography = Typography(
    titleLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp),
    titleMedium = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
    bodyLarge = TextStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall = TextStyle(fontWeight = FontWeight.Normal, fontSize = 11.sp),
    labelLarge = TextStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp)
)
