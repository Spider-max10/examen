package com.example.proveedor.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.proveedor.database.dao.ProductoDao
import com.example.proveedor.database.dao.ProveedorDao
import com.example.proveedor.database.entidades.ProductoEntidad
import com.example.proveedor.database.entidades.ProveedorEntidad


@Database(entities = [ProveedorEntidad::class, ProductoEntidad::class], version = 3)
abstract class ProveedorDB: RoomDatabase(){
    abstract fun getProveedorDao(): ProveedorDao
    abstract fun getProductoDao(): ProductoDao
}