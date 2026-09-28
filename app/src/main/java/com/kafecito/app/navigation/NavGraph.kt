package com.kafecito.app.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kafecito.app.data.repository.KafecitoRepository
import com.kafecito.app.ui.components.KafeBottomBar
import com.kafecito.app.ui.screens.*
import com.kafecito.app.viewmodel.CarritoViewModel

object Rutas {
    const val WELCOME = "welcome"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val CONTACTO = "contacto"
    const val PERFIL = "perfil"
    const val CARRITO = "carrito"
    const val DETALLE = "detalle/{productoId}"

    fun detalle(productoId: Int) = "detalle/$productoId"
}

// Pantallas que muestran la barra de navegación inferior.
private val rutasConBarra = setOf(Rutas.HOME, Rutas.CONTACTO, Rutas.PERFIL)

/**
 * Mapa de navegación de toda la app (requisito 3 del Avance 1).
 *
 * Flujo principal:  Welcome -> Login / Registro -> Home -> Detalle -> Carrito
 * Barra inferior:   Home | Contacto | Perfil
 */
@Composable
fun KafecitoNavGraph(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination?.route

    // Un solo carrito compartido por Home, Detalle y Carrito.
    val carritoVM: CarritoViewModel = viewModel()

    Scaffold(
        bottomBar = {
            if (rutaActual in rutasConBarra) {
                KafeBottomBar(rutaActual = rutaActual) { destino ->
                    navController.navigate(destino) {
                        popUpTo(Rutas.HOME) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Rutas.WELCOME,
            modifier = Modifier.padding(padding)
        ) {
            composable(Rutas.WELCOME) {
                WelcomeScreen(
                    onIniciarSesion = { navController.navigate(Rutas.LOGIN) },
                    onCrearCuenta = { navController.navigate(Rutas.REGISTER) }
                )
            }

            composable(Rutas.LOGIN) {
                LoginScreen(
                    onLoginExitoso = {
                        navController.navigate(Rutas.HOME) { popUpTo(Rutas.WELCOME) { inclusive = true } }
                    },
                    onAtras = { navController.popBackStack() }
                )
            }

            composable(Rutas.REGISTER) {
                RegisterScreen(
                    onCuentaCreada = {
                        navController.navigate(Rutas.HOME) { popUpTo(Rutas.WELCOME) { inclusive = true } }
                    },
                    onAtras = { navController.popBackStack() }
                )
            }

            composable(Rutas.HOME) {
                HomeScreen(
                    onAbrirCarrito = { navController.navigate(Rutas.CARRITO) },
                    onAbrirDetalle = { id -> navController.navigate(Rutas.detalle(id)) },
                    onAgregar = { producto -> carritoVM.agregar(producto) }
                )
            }

            composable(
                route = Rutas.DETALLE,
                arguments = listOf(navArgument("productoId") { type = NavType.IntType })
            ) { entry ->
                val id = entry.arguments?.getInt("productoId") ?: -1
                ProductDetailScreen(
                    producto = KafecitoRepository.obtenerProducto(id),
                    onAtras = { navController.popBackStack() },
                    onAgregar = { producto, cantidad -> carritoVM.agregar(producto, cantidad) }
                )
            }

            composable(Rutas.CARRITO) {
                CartScreen(
                    carritoVM = carritoVM,
                    onAtras = { navController.popBackStack() }
                )
            }

            composable(Rutas.CONTACTO) { ContactScreen() }

            composable(Rutas.PERFIL) {
                ProfileScreen(
                    onCerrarSesion = {
                        KafecitoRepository.cerrarSesion()
                        carritoVM.vaciar()
                        navController.navigate(Rutas.WELCOME) {
                            popUpTo(Rutas.HOME) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
