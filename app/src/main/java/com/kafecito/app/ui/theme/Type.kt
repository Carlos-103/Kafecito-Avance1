package com.kafecito.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ============================================================================
// TODO(TEMA): TIPOGRAFÍA SEGÚN EL FIGMA
//        titleLarge  -> títulos grandes ("Bienvenido de nuevo", "Crear cuenta", KAFECITO)
//        titleMedium -> encabezados de pantalla ("¡Hola! David", "PERFIL", "MI PEDIDO")
//        bodyLarge   -> nombres de producto y texto normal
//        bodyMedium  -> descripciones, etiquetas
//        bodySmall   -> texto pequeño (descripción dentro de las tarjetas)
// ============================================================================

val Typography = Typography(
    titleLarge = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp),
    titleMedium = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
    bodyLarge = TextStyle(fontWeight = FontWeight.Medium, fontSize = 16.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall = TextStyle(fontWeight = FontWeight.Normal, fontSize = 13.sp),
    labelLarge = TextStyle(fontWeight = FontWeight.Medium, fontSize = 14.sp)
)
