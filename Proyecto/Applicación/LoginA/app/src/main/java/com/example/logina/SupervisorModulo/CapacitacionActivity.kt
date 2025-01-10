package com.example.logina.SupervisorModulo

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.logina.R

class CapacitacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capacitacion)

        val tableLayout = findViewById<TableLayout>(R.id.capacitacionTableLayout)

        // Encabezados de la tabla
        val headerRow = TableRow(this)
        val headers = listOf("Nombre", "Zona", "Centro Educativo", "Estado", "Acción")
        headers.forEach { header ->
            val textView = TextView(this).apply {
                text = header
                textSize = 16f
                setPadding(8, 8, 8, 8)
            }
            headerRow.addView(textView)
        }
        tableLayout.addView(headerRow)

        // Datos de ejemplo
        val becarios = listOf(
            listOf("Juan Pérez", "A", "Centro 2", "En espera", true),
            listOf("María López", "B", "Centro 3", "En capacitación", false),
            listOf("Carlos Ruiz", "C", "Centro 4", "En espera", true)
        )

        // Agregar filas para cada becario
        becarios.forEach { becario ->
            val row = TableRow(this)

            // Nombre
            val nameTextView = TextView(this).apply {
                text = becario[0] as String
                textSize = 14f
                setPadding(8, 8, 8, 8)
            }
            row.addView(nameTextView)

            // Zona
            val zonaTextView = TextView(this).apply {
                text = becario[1] as String
                textSize = 14f
                setPadding(8, 8, 8, 8)
            }
            row.addView(zonaTextView)

            // Centro Educativo
            val centroTextView = TextView(this).apply {
                text = becario[2] as String
                textSize = 14f
                setPadding(8, 8, 8, 8)
            }
            row.addView(centroTextView)

            // Estado
            val statusTextView = TextView(this).apply {
                text = becario[3] as String
                textSize = 14f
                setPadding(8, 8, 8, 8)
            }
            row.addView(statusTextView)

            // Botón de acción
            val actionButton = Button(this).apply {
                text = if (becario[4] as Boolean) "Mandar a capacitación" else "-"
                isEnabled = becario[4] as Boolean
                setOnClickListener {
                    statusTextView.text = "En capacitación"
                    isEnabled = false
                }
            }
            row.addView(actionButton)

            tableLayout.addView(row)
        }
    }
}
