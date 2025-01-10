package com.example.logina.CordinadorZona

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logina.DataBase.DatabaseConfig
import com.example.logina.DataBase.UsuariosTable
import com.example.logina.R
import com.example.logina.Aspirantes.Aspirante
import com.example.logina.Aspirantes.AspirantesAdapter

class CandidatosActivity : AppCompatActivity() {

    private val aspirantesList = mutableListOf<Aspirante>()
    private lateinit var dbConfig: DatabaseConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidatos)

        // Inicializar DatabaseConfig
        dbConfig = DatabaseConfig(this)

        // Cargar aspirantes desde la base de datos
        loadAspirantes()

        val recyclerView = findViewById<RecyclerView>(R.id.candidatosRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AspirantesAdapter(aspirantesList) { aspirante, action ->
            when (action) {
                "accept" -> handleAcceptAction(aspirante)
                "reject" -> handleRejectAction(aspirante)
            }
        }
    }

    private fun loadAspirantes() {
        aspirantesList.clear()
        val db = dbConfig.readableDatabase

        // Consulta para obtener usuarios con tipo de cuenta "Becario"
        val query = """
            SELECT ${UsuariosTable.COLUMN_ID}, 
                   ${UsuariosTable.COLUMN_NOMBRE}, 
                   ${UsuariosTable.COLUMN_CORREO} 
            FROM ${UsuariosTable.TABLE_NAME} 
            WHERE ${UsuariosTable.COLUMN_TIPO_CUENTA} = 'Becario'
        """.trimIndent()

        val cursor = db.rawQuery(query, null)
        cursor.use {
            while (it.moveToNext()) {
                val id = it.getInt(it.getColumnIndexOrThrow(UsuariosTable.COLUMN_ID))
                val nombre = it.getString(it.getColumnIndexOrThrow(UsuariosTable.COLUMN_NOMBRE))
                val correo = it.getString(it.getColumnIndexOrThrow(UsuariosTable.COLUMN_CORREO))
                aspirantesList.add(Aspirante(id, nombre, correo))
            }
        }
    }

    private fun handleAcceptAction(aspirante: Aspirante) {
        Toast.makeText(this, "Aspirante ${aspirante.nombre} aceptado", Toast.LENGTH_SHORT).show()
    }

    private fun handleRejectAction(aspirante: Aspirante) {
        Toast.makeText(this, "Aspirante ${aspirante.nombre} rechazado", Toast.LENGTH_SHORT).show()
    }
}
