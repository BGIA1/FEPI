package com.example.logina.DataBase

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

object UsuariosTable {
    const val TABLE_NAME = "usuarios"
    const val COLUMN_ID = "id"
    const val COLUMN_NOMBRE = "nombre"
    const val COLUMN_APELLIDOS = "apellidos"
    const val COLUMN_CORREO = "correo"
    const val COLUMN_FECHA_NACIMIENTO = "fecha_nacimiento"
    const val COLUMN_CELULAR = "numero_celular"
    const val COLUMN_CONTRASENA = "contrasena"
    const val COLUMN_TIPO_CUENTA = "tipo_cuenta"

    fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_APELLIDOS TEXT NOT NULL,
                $COLUMN_CORREO TEXT NOT NULL UNIQUE,
                $COLUMN_FECHA_NACIMIENTO TEXT NOT NULL,
                $COLUMN_CELULAR TEXT NOT NULL,
                $COLUMN_CONTRASENA TEXT NOT NULL,
                $COLUMN_TIPO_CUENTA TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    fun onUpgrade(db: SQLiteDatabase) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    /**
     * Agrega un nuevo usuario a la base de datos.
     * Si el tipo de cuenta es "Becario", crea automáticamente un registro en CandidatosTable.
     */
    fun addUsuario(
        db: SQLiteDatabase,
        nombre: String,
        apellidos: String,
        correo: String,
        fechaNacimiento: String,
        celular: String,
        contrasena: String,
        tipoCuenta: String
    ): Long {
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, nombre)
            put(COLUMN_APELLIDOS, apellidos)
            put(COLUMN_CORREO, correo)
            put(COLUMN_FECHA_NACIMIENTO, fechaNacimiento)
            put(COLUMN_CELULAR, celular)
            put(COLUMN_CONTRASENA, contrasena)
            put(COLUMN_TIPO_CUENTA, tipoCuenta)
        }

        return try {
            val userId = db.insertOrThrow(TABLE_NAME, null, values)
            userId
        } catch (e: Exception) {
            -1 // Devuelve -1 si ocurre un error (por ejemplo, violación de la restricción UNIQUE)
        }
    }

    /**
     * Obtiene todos los usuarios de la base de datos.
     */
    fun getAllUsuarios(db: SQLiteDatabase): List<Usuario> {
        val usuarios = mutableListOf<Usuario>()
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(
                COLUMN_ID,
                COLUMN_NOMBRE,
                COLUMN_APELLIDOS,
                COLUMN_CORREO,
                COLUMN_FECHA_NACIMIENTO,
                COLUMN_CELULAR,
                COLUMN_CONTRASENA,
                COLUMN_TIPO_CUENTA
            ),
            null,
            null,
            null,
            null,
            null
        )
        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow(COLUMN_ID))
                val nombre = it.getString(it.getColumnIndexOrThrow(COLUMN_NOMBRE))
                val apellidos = it.getString(it.getColumnIndexOrThrow(COLUMN_APELLIDOS))
                val correo = it.getString(it.getColumnIndexOrThrow(COLUMN_CORREO))
                val fechaNacimiento = it.getString(it.getColumnIndexOrThrow(COLUMN_FECHA_NACIMIENTO))
                val celular = it.getString(it.getColumnIndexOrThrow(COLUMN_CELULAR))
                val contrasena = it.getString(it.getColumnIndexOrThrow(COLUMN_CONTRASENA))
                val tipoCuenta = it.getString(it.getColumnIndexOrThrow(COLUMN_TIPO_CUENTA))
                usuarios.add(Usuario(id, nombre, apellidos, correo, fechaNacimiento, celular, contrasena, tipoCuenta))
            }
        }
        return usuarios
    }
}