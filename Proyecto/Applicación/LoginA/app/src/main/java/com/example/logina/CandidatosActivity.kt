package com.example.logina

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CandidatosActivity : AppCompatActivity() {

    private val candidatosList = mutableListOf<Candidato>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidatos)

        // Simula una lista de candidatos
        candidatosList.add(Candidato("Juan Pérez", "juan@gmail.com", "1234567890"))
        candidatosList.add(Candidato("Ana López", "ana@gmail.com", "0987654321"))

        val recyclerView = findViewById<RecyclerView>(R.id.candidatosRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CandidatosAdapter(candidatosList) { candidato, action, reason ->
            when (action) {
                "view" -> {
                    Toast.makeText(this, "Mostrando documentos de ${candidato.nombre}", Toast.LENGTH_SHORT).show()
                }
                "accept" -> {
                    Toast.makeText(this, "${candidato.nombre} aceptado", Toast.LENGTH_SHORT).show()
                }
                "reject" -> {
                    Toast.makeText(this, "Rechazado: $reason", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
