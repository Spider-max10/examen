package com.example.proveedor.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proveedor.screens.ProductoScreen
import com.example.proveedor.screens.ProveedoresScreen

@Composable
fun AppNavigation(scrollState: ScrollState) {
    val navController = rememberNavController()
    NavHost(navController =navController , startDestination = AppScreens.Proveedores.route){
        composable(route = AppScreens.Proveedores.route){
            ProveedoresScreen(navController, scrollState = scrollState)
        }
        composable(route = AppScreens.Producto.route + "/{proveedorId}") { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val proveedorId = arguments.getString("proveedorId")
            ProductoScreen(proveedorId)
        }


    }

}