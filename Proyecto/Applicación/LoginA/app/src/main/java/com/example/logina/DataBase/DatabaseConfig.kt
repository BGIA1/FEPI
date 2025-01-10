package com.example.logina.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseConfig(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "DB_CONAFE.db"
        const val DATABASE_VERSION = 17 // Incrementa la versión de la base de datos
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crear tablas iniciales
        ConvocatoriasTable.onCreate(db)
        UsuariosTable.onCreate(db) // Agregar tabla de usuarios
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Actualizar tablas
        ConvocatoriasTable.onUpgrade(db)
        UsuariosTable.onUpgrade(db) // Actualizar tabla de usuarios
    }
}
