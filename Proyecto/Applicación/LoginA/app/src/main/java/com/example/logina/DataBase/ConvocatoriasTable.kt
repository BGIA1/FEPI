package com.example.logina.DataBase

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

object ConvocatoriasTable {
    const val TABLE_NAME = "convocatorias"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_START_DATE = "start_date"
    const val COLUMN_END_DATE = "end_date"

    fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_START_DATE TEXT NOT NULL,
                $COLUMN_END_DATE TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    fun onUpgrade(db: SQLiteDatabase) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addConvocatoria(db: SQLiteDatabase, name: String, startDate: String, endDate: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_START_DATE, startDate)
            put(COLUMN_END_DATE, endDate)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllConvocatorias(db: SQLiteDatabase): List<Convocatoria> {
        val convocatorias = mutableListOf<Convocatoria>()
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_NAME, COLUMN_START_DATE, COLUMN_END_DATE),
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                val name = it.getString(it.getColumnIndexOrThrow(COLUMN_NAME))
                val startDate = it.getString(it.getColumnIndexOrThrow(COLUMN_START_DATE))
                val endDate = it.getString(it.getColumnIndexOrThrow(COLUMN_END_DATE))
                convocatorias.add(Convocatoria(name, startDate, endDate))
            }
        }
        return convocatorias
    }

    fun deleteConvocatoria(db: SQLiteDatabase, name: String, startDate: String, endDate: String): Int {
        val selection = "$COLUMN_NAME = ? AND $COLUMN_START_DATE = ? AND $COLUMN_END_DATE = ?"
        val selectionArgs = arrayOf(name, startDate, endDate)
        return db.delete(TABLE_NAME, selection, selectionArgs)
    }
}
