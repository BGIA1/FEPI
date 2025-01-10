package com.example.logina.SupervisorModulo

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logina.R

class SupervisionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supervision)

        val supervisionLayout = findViewById<LinearLayout>(R.id.supervisionLayout)

        // Lista de docentes con datos de ejemplo
        val docentes = listOf(
            Pair("Docente 1", "Ubicación 1"),
            Pair("Docente 2", "Ubicación 2"),
            Pair("Docente 3", "Ubicación 3")
        )

        // Lista para almacenar las evaluaciones
        val evaluaciones = mutableListOf<Pair<String, String>>()

        // Agregar información de cada docente a la vista
        docentes.forEach { docente ->
            // Contenedor para cada docente
            val docenteContainer = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
            }

            // Nombre del docente
            val nombreTextView = TextView(this).apply {
                text = "Nombre: ${docente.first}"
                textSize = 16f
                setPadding(0, 0, 0, 8)
            }
            docenteContainer.addView(nombreTextView)

            // Ubicación del docente
            val ubicacionTextView = TextView(this).apply {
                text = "Ubicación: ${docente.second}"
                textSize = 16f
                setPadding(0, 0, 0, 8)
            }
            docenteContainer.addView(ubicacionTextView)

            // Grupo de opciones para evaluación
            val radioGroup = RadioGroup(this).apply {
                orientation = RadioGroup.HORIZONTAL
            }

            val okButton = RadioButton(this).apply {
                text = "OK"
                textSize = 14f
            }
            val regularButton = RadioButton(this).apply {
                text = "Regular"
                textSize = 14f
            }
            val noOkButton = RadioButton(this).apply {
                text = "No-OK"
                textSize = 14f
            }

            radioGroup.addView(okButton)
            radioGroup.addView(regularButton)
            radioGroup.addView(noOkButton)

            docenteContainer.addView(radioGroup)

            // Almacenar evaluación seleccionada
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                val evaluacion = when (checkedId) {
                    okButton.id -> "OK"
                    regularButton.id -> "Regular"
                    noOkButton.id -> "No-OK"
                    else -> "Sin evaluar"
                }
                evaluaciones.removeIf { it.first == docente.first }
                evaluaciones.add(Pair(docente.first, evaluacion))
            }

            // Agregar el contenedor del docente al layout principal
            supervisionLayout.addView(docenteContainer)
        }

        // Botón para enviar evaluaciones
        val enviarButton = Button(this).apply {
            text = "Enviar Evaluaciones"
            textSize = 16f
            setOnClickListener {
                if (evaluaciones.size < docentes.size) {
                    Toast.makeText(this@SupervisionActivity, "Por favor, evalúa a todos los docentes antes de enviar.", Toast.LENGTH_SHORT).show()
                } else {
                    evaluaciones.forEach { evaluacion ->
                        println("Docente: ${evaluacion.first}, Evaluación: ${evaluacion.second}")
                    }
                    Toast.makeText(this@SupervisionActivity, "Evaluaciones enviadas correctamente.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        supervisionLayout.addView(enviarButton)
    }
}
