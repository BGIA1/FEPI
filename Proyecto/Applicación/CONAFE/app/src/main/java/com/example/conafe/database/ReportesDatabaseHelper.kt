package com.example.conafe.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ReportesDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE reportes (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                alumno TEXT,
                desempeno TEXT,
                comentarios TEXT
            )
        """
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS reportes")
        onCreate(db)
    }

    fun guardarReporte(alumno: String, desempeno: String, comentarios: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("alumno", alumno)
            put("desempeno", desempeno)
            put("comentarios", comentarios)
        }
        return db.insert("reportes", null, values)
    }

    companion object {
        const val DATABASE_NAME = "ReportesDB"
        const val DATABASE_VERSION = 1
    }
}
