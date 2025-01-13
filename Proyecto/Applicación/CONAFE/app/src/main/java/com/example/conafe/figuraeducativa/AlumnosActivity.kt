package com.example.conafe.figuraeducativa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conafe.R

class AlumnosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumnos)

        // Configurar la lista de alumnos
        val recyclerView = findViewById<RecyclerView>(R.id.rvAlumnos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Datos de ejemplo
        val alumnos = listOf(
            "ID: 1 - Juan Pérez",
            "ID: 2 - Ana Gómez",
            "ID: 3 - Carlos López",
            "ID: 4 - María Hernández",
            "ID: 5 - Pedro Martínez"
        )

        // Adaptador
        val adapter = AlumnosAdapter(alumnos)
        recyclerView.adapter = adapter
    }
}
