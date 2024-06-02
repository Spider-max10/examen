package com.example.proveedor.database.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "productos",
    foreignKeys = [
        ForeignKey(entity = ProveedorEntidad::class, parentColumns = ["id"], childColumns = ["idProveedor"])
    ]
)
data class ProductoEntidad(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "nombre") val Nombre: String,
    @ColumnInfo(name = "descripcion") val Descripcion: String,
    @ColumnInfo(name = "precio") val Precio: Int,
    @ColumnInfo(name = "idProveedor") val IdProveedor: Int,
    @ColumnInfo(name = "imagen") val Imagen: String
)