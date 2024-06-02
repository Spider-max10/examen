package com.example.proveedor.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proveedor.Carga
import com.example.proveedor.database.entidades.ProductoEntidad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductoViewModel : ViewModel() {

    private val _productos = mutableStateListOf<ProductoEntidad>()
    val productos: SnapshotStateList<ProductoEntidad> get() = _productos

    init {
        ProductoresPorDefecto()
    }

    private fun ProductoresPorDefecto() {
        viewModelScope.launch {
            val DbVacia = withContext(Dispatchers.IO) {
                Carga.room.getProductoDao().getAllProductos().isEmpty()
            }
            if (DbVacia) {
                val defaultProductos = listOf(
                    ProductoEntidad(1, "Coca cola personal", "Gaseosa negra cara de tamaño pequeño.", 1 , 1,"https://n9.cl/2rpms"),
                    ProductoEntidad(2, "Galletas Festival pequeñas", "Paquete con 4 galletas, con sabor a elección.", 1, 2,"https://n9.cl/qsh6b"),
                    ProductoEntidad(3, "Six Pack Pilsener", "Paquete de 6 latas de cervezas", 4, 3,"https://n9.cl/k8oig"),
                    ProductoEntidad(4, "Fiora Vanti familiar", "Gaseosa de fresa de 3 litros..", 3 , 1,"https://n9.cl/pzf4i"),
                    ProductoEntidad(5, "Nesquik", "Cereal de chocolate", 2 , 4,"https://n9.cl/wl461"),
                    ProductoEntidad(6, "Sanduche", "Helado tipo sanduche con chocolate y vainilla", 1 , 5,"https://n9.cl/nf9kp")
                    )
                withContext(Dispatchers.IO) {
                    Carga.room.getProductoDao().insert(defaultProductos)
                }
                _productos.addAll(defaultProductos)
            }
        }
    }

    fun fetchProducto(productoIdInt: Int) {
        viewModelScope.launch {
            val loadedProductos = withContext(Dispatchers.IO) {
                Carga.room.getProductoDao().getProductosById(productoIdInt)
            }
            println(loadedProductos)
            _productos.clear()
            _productos.addAll(loadedProductos)
        }
    }
}