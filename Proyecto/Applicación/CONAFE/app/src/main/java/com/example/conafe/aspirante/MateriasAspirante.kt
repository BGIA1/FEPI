package com.example.conafe.aspirante

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R

class MateriasAspirante : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tabla_materias)

        // Referencia al TableLayout y botón
        val tableLayout = findViewById<TableLayout>(R.id.tableLayoutMaterias)
        val btnGenerarCertificado = findViewById<Button>(R.id.btnGenerarCertificado)

        // Encabezado dinámico enviado desde el código
        val nombreEncabezado = "Nivel Primaria"
        val tvEncabezado = findViewById<TextView>(R.id.tvEncabezado)
        tvEncabezado.text = nombreEncabezado

        // Datos para llenar la tabla
        val registros = listOf(
            listOf("1", "Matemáticas", "90", "Sí"),
            listOf("2", "Español", "85", "No"),
            listOf("3", "Historia", "88", "Sí"),
        )

        // Agregar encabezados de la tabla
        val headerRow = TableRow(this)
        val headers = listOf("ID", "MATERIA", "CALIFICACIÓN", "EX")
        for (header in headers) {
            val textViewHeader = TextView(this)
            textViewHeader.text = header
            textViewHeader.textSize = 18f
            textViewHeader.setPadding(16, 16, 16, 16)
            textViewHeader.setBackgroundResource(android.R.color.darker_gray)
            textViewHeader.setTextColor(resources.getColor(android.R.color.white))
            headerRow.addView(textViewHeader)
        }
        tableLayout.addView(headerRow)

        // Agregar filas de datos
        for (registro in registros) {
            val dataRow = TableRow(this)
            for (dato in registro) {
                val textViewData = TextView(this)
                textViewData.text = dato
                textViewData.textSize = 16f
                textViewData.setPadding(16, 16, 16, 16)
                dataRow.addView(textViewData)
            }
            tableLayout.addView(dataRow)
        }

        // Acción del botón "Generar Certificado"
        btnGenerarCertificado.setOnClickListener {
            Toast.makeText(this, "Certificado generado exitosamente", Toast.LENGTH_SHORT).show()
        }
    }
}
