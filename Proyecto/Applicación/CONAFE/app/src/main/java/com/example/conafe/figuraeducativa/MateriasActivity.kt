package com.example.conafe.figuraeducativa

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conafe.R
import com.example.conafe.figuraeducativa.MateriasAdapter



class MateriasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        // Lista de materias
        val materias = listOf(
            Materia("Matemáticas", "", ""),
            Materia("Español", "", ""),
            Materia("Ciencias", "", ""),
            Materia("Historia", "", ""),
            Materia("Geografía", "", "")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rvMaterias)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adaptador con el callback para guardar la calificación
        val adapter = MateriasAdapter(materias) { materia, calificacion ->
            // Aquí puedes manejar el guardado de la calificación
            Toast.makeText(this, "Guardado ${materia.nombre}: $calificacion", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = adapter
    }
}

