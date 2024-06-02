package com.example.proveedor.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proveedor.Carga
import com.example.proveedor.database.entidades.ProveedorEntidad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProveedoresViewModel : ViewModel() {

    private val _proveedores = mutableStateListOf<ProveedorEntidad>()
    val proveedores: SnapshotStateList<ProveedorEntidad> get() = _proveedores

    init {
        fetchProveedores()
        ProveedoresPorDefecto()
    }

    private fun ProveedoresPorDefecto() {
        viewModelScope.launch {
            val DbVacia = withContext(Dispatchers.IO) {
                Carga.room.getProveedorDao().getAllProveedores().isEmpty()
            }
            if (DbVacia) {
                val defaultProveedores = listOf(
                    ProveedorEntidad(1, "Coca Cola", "Empresa con variedad de productos, predominando las gaseosas.", 1904,"https://n9.cl/ki0au"),
                    ProveedorEntidad(2, "Festival", "Empresa de golosinas.", 1950,"https://n9.cl/1c6q4"),
                    ProveedorEntidad(3, "Cervecería Nacional CN S.A.", " Primera compañía dedicada a la elaboración y comercialización de bebidas de moderación y refrescos en Ecuador.", 1887, "https://n9.cl/zxn8e3"),
                    ProveedorEntidad(4, "Nestle", " Empresa multinacional de productos variados de comida", 1866, "https://n9.cl/jkc7s"),
                    ProveedorEntidad(5, "Pinguino SA", " Empresa de helados", 1910, "https://n9.cl/fqanb")
                )
                withContext(Dispatchers.IO) {
                    Carga.room.getProveedorDao().insert(defaultProveedores)
                }
                _proveedores.addAll(defaultProveedores)
            }
        }
    }

    private fun fetchProveedores() {
        viewModelScope.launch {
            val loadedProveedores = withContext(Dispatchers.IO) {
                Carga.room.getProveedorDao().getAllProveedores()
            }
            _proveedores.clear()
            _proveedores.addAll(loadedProveedores)
        }
    }
}
