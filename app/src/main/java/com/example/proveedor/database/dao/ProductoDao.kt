package com.example.proveedor.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proveedor.database.entidades.ProductoEntidad

@Dao
interface ProductoDao {

    @Query("SELECT * FROM productos" )
    suspend fun getAllProductos():List<ProductoEntidad>

    @Query("SELECT * FROM productos WHERE idProveedor = :idProveedor" )
    suspend fun getProductosById(idProveedor: Int): List<ProductoEntidad>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productos: List<ProductoEntidad>)
}