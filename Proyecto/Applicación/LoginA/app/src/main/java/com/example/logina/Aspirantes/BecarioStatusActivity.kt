package com.example.logina.Aspirantes

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.logina.DataBase.DatabaseConfig
import com.example.logina.DataBase.UsuariosTable
import com.example.logina.R
import com.example.logina.SessionManager

class BecarioStatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_becario_status)

        val tableLayout = findViewById<TableLayout>(R.id.statusTableLayout)

        // Obtener correo del usuario de la sesión
        val sessionManager = SessionManager(this)
        val userEmail = sessionManager.getUserEmail()

        if (userEmail == null) {
            // Si no hay correo en la sesión, termina la actividad
            finish()
            return
        }

        // Realizar query para obtener el ID del usuario logueado
        val dbHelper = DatabaseConfig(this)
        val db = dbHelper.readableDatabase

        val userCursor = db.query(
            UsuariosTable.TABLE_NAME,
            arrayOf(UsuariosTable.COLUMN_ID),
            "${UsuariosTable.COLUMN_CORREO} = ?",
            arrayOf(userEmail),
            null,
            null,
            null
        )

        if (userCursor.moveToFirst()) {
            val userId = userCursor.getInt(userCursor.getColumnIndexOrThrow(UsuariosTable.COLUMN_ID))

            // Mostrar mensaje de usuario encontrado
            val noDataRow = TableRow(this)
            val noDataTextView = TextView(this).apply {
                text = "ID de usuario logueado: $userId"
                textSize = 14f
                setPadding(16, 16, 16, 16)
            }
            noDataRow.addView(noDataTextView)
            tableLayout.addView(noDataRow)
        } else {
            // Manejar el caso en el que no se encuentre el ID del usuario
            val noDataRow = TableRow(this)
            val noDataTextView = TextView(this).apply {
                text = "No se encontró el ID del usuario logueado."
                textSize = 14f
                setPadding(16, 16, 16, 16)
            }
            noDataRow.addView(noDataTextView)
            tableLayout.addView(noDataRow)
        }

        userCursor.close()
    }
}
