package com.example.proveedor

import android.app.Application
import androidx.room.Room
import com.example.proveedor.database.ProveedorDB

class Carga: Application() {

    companion object{
        lateinit var room: ProveedorDB
    }

    override fun onCreate() {
        super.onCreate()
        room = Room
            .databaseBuilder(applicationContext, ProveedorDB::class.java, "proveedor_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}