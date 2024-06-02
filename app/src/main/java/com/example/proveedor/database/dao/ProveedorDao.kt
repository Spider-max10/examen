package com.example.proveedor.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proveedor.database.entidades.ProveedorEntidad

@Dao
interface ProveedorDao {

    @Query("SELECT * FROM proveedores" )
    suspend fun getAllProveedores():List<ProveedorEntidad>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(proveedores: List<ProveedorEntidad>)

}