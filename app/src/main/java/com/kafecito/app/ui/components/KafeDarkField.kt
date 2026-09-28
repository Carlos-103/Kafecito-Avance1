package com.kafecito.app.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.kafecito.app.ui.theme.KafeBorderDark
import com.kafecito.app.ui.theme.KafeGray
import com.kafecito.app.ui.theme.KafeWhite

/** Campo de texto para pantallas de fondo oscuro (Login y Registro). */
@Composable
fun KafeDarkField(
    label: String,
    value: String,
    onChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    esPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label, color = KafeGray) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (esPassword) PasswordVisualTransformation() else VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = KafeWhite,
            unfocusedTextColor = KafeWhite,
            focusedBorderColor = KafeWhite,
            unfocusedBorderColor = KafeBorderDark,
            cursorColor = KafeWhite,
            focusedLabelColor = KafeWhite,
            unfocusedLabelColor = KafeGray
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
