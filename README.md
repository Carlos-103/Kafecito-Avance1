# Kafecito — Android (Etapa 3, Avance 1)

Proyecto Kotlin + Jetpack Compose basado en el Figma KAFECITO APP.

**Alcance del Avance 1:** el flujo principal completo
`Crear cuenta / Login → Catálogo → Detalle de producto → Carrito`.

---

## Cómo abrirlo

1. Abrir la carpeta KafecitoAndroid desde Android Studio (**Open**) y esperar el Gradle Sync.
2. **⚠️ Importante (Error de Java):** Si al sincronizar sale el error _"Incompatible Gradle JVM version"_:
   - Ve a **File → Settings → Build, Execution, Deployment → Build Tools → Gradle**.
   - En **Gradle JDK**, selecciona una versión de Java compatible (como la **17** o la **21**). Si no tienes ninguna en la lista, elige la opción **"Download JDK..."** dentro de ese mismo menú para descargarla rápidamente.
   - Dale a **Apply / OK** y vuelve a sincronizar tocando el ícono del elefantito (Sync Project) arriba a la derecha.
3. Ejecutar en un celular por USB (con depuración USB activada).
4. Usuario de prueba: admin@kafecito.com / 123456, o crear una cuenta nueva en "Crear cuenta".

## Cómo encontrar lo que falta

Cada pendiente está en el código como comentario `TODO(ETIQUETA)` con las instrucciones paso a paso.
En Android Studio: **View → Tool Windows → TODO**, y buscar por la etiqueta (por ejemplo `TODO(CARRITO)`).

---

## Qué ya funciona

- Welcome, Login y Registro con validaciones (campos vacíos, formato de correo, contraseña de mínimo 6 caracteres, contraseñas iguales, términos aceptados, correo ya registrado).
- El registro guarda la cuenta y el login la valida (en memoria).
- Home: saludo con el nombre del usuario, buscador, categorías, grid de productos.
- Tocar una tarjeta abre el Detalle; el botón "+" agrega al carrito.
- Navegación completa con barra inferior (Home, Contacto, Perfil).
- Perfil con nombre y correo del usuario y "Cerrar sesión".

## Qué falta (etiquetas)

| Etiqueta         | Dónde                                                                                              | Qué hay que hacer                                                                                                                                                                                                                                    |
| ---------------- | -------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `TODO(TEMA)`     | `ui/theme/Color.kt`, `ui/theme/Type.kt`, `WelcomeScreen.kt`                                        | Buscar los códigos de color (ej. `#FF0000`) y tamaños de texto en el diseño de Figma y copiarlos aquí. Solo hay que cambiar los valores y números; los nombres de las variables se dejan intactos.                                                   |
| `TODO(IMAGENES)` | `HomeScreen.kt`, `ProductDetailScreen.kt`, `CartScreen.kt`, `Producto.kt`, `KafecitoRepository.kt` | Poner la URL de la foto de cada producto en `imagenUrl` (`KafecitoRepository.kt`) y reemplazar los cuadros grises de `HomeScreen.kt`, `ProductDetailScreen.kt` y `CartScreen.kt` por `AsyncImage`. Coil y el permiso de internet ya están agregados. |
| `TODO(CATALOGO)` | `data/repository/KafecitoRepository.kt`                                                            | Abrir este archivo y borrar la lista de productos de prueba. Luego, copiar y pegar la información real (nombres de cafés, precios, descripciones) que teníamos en el archivo `Inventario.kt` de la Etapa 2.                                          |
| `TODO(DETALLE)`  | `ui/screens/ProductDetailScreen.kt`                                                                | Hacer que funcionen los botones de "+" y "-" para elegir cuántos cafés llevar (el mínimo es 1 y el máximo es el stock disponible). Además, lograr que el botón de "Agregar pedido" calcule y muestre automáticamente el precio total a pagar.        |
| `TODO(CARRITO)`  | `viewmodel/CarritoViewModel.kt`, `ui/screens/CartScreen.kt`                                        | Darle vida a la pantalla del carrito: que funcionen los botones para sumar/restar/eliminar productos, la opción de vaciar todo, y programar la matemática para que calcule el Subtotal, el IVA (13%) y el Total final.                               |

Además, para la entrega del Avance 1:

- **PDF de investigación (APA 7)** sobre Jetpack Compose aplicado al proyecto (sin él la nota base es 5).
- **Video demostración** (máx. 15 min): registro, login, catálogo, detalle, carrito, navegación.

---

## Estructura

```
app/src/main/java/com/kafecito/app/
├── MainActivity.kt
├── navigation/NavGraph.kt          Rutas y barra inferior
├── data/model/                     Usuario, Producto, ItemCarrito
├── data/repository/                KafecitoRepository (datos en memoria)
├── viewmodel/                      LoginViewModel, CarritoViewModel
├── util/Validaciones.kt            Validaciones de formularios
└── ui/
    ├── theme/                      Color.kt, Type.kt, Theme.kt
    ├── components/                 KafeBottomBar, KafeDarkField
    └── screens/                    Welcome, Login, Register, Home, ProductDetail, Cart,
                                    Profile, Contact
```

## Trabajo en equipo (Git)

- Cada integrante trabaja en **su propia rama** (`git checkout -b nombre-tarea`), nunca directo en `main`.
- Al terminar: commit, push y Pull Request; el dueño del repositorio revisa y hace el merge.
- Todos deben quedar como colaboradores del repositorio y con commits propios (requisito de la entrega final).
