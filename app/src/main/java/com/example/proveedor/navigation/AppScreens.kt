package com.example.proveedor.navigation

sealed class AppScreens(val route: String){

    object Proveedores: AppScreens("Proveedores")
    object Producto: AppScreens("Producto/{proveedorId}")

}
