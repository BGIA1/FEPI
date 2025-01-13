package com.example.conafe.coordinador

import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R

class RecursosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_recursos)

        // Referencia al TableLayout
        val tableLayout = findViewById<TableLayout>(R.id.tableLayoutRecursos)

        // Datos simulados
        val registros = mutableListOf("Recurso 1", "Recurso 2")
        val estado = mutableListOf(0, 0) // Estados iniciales: 0 no procesado, 1 procesado
        val opcionesAccion = listOf("Aceptar", "Rechazar")

        // Método para cargar la tabla
        fun cargarTabla() {
            tableLayout.removeAllViews()

            // Agregar encabezados de la tabla
            val headerRow = TableRow(this)
            val headers = listOf("Recurso", "Acción", "Enviar")
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

            // Agregar filas dinámicamente
            for ((index, recurso) in registros.withIndex()) {
                if (estado[index] == 0) { // Solo mostrar recursos no procesados
                    val dataRow = TableRow(this)

                    // Columna: Recurso
                    val textViewRecurso = TextView(this)
                    textViewRecurso.text = recurso
                    textViewRecurso.textSize = 16f
                    textViewRecurso.setPadding(16, 16, 16, 16)
                    dataRow.addView(textViewRecurso)

                    // Columna: Menú desplegable
                    val spinner = Spinner(this)
                    val adapter = android.widget.ArrayAdapter(
                        this,
                        android.R.layout.simple_spinner_item,
                        opcionesAccion
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter
                    dataRow.addView(spinner)

                    // Columna: Botón Enviar
                    val buttonEnviar = Button(this)
                    buttonEnviar.text = "Enviar"
                    buttonEnviar.setOnClickListener {
                        val opcionSeleccionada = spinner.selectedItem.toString()
                        Toast.makeText(this, "$recurso $opcionSeleccionada", Toast.LENGTH_SHORT).show()

                        // Actualizar el estado del recurso a procesado
                        estado[index] = 1

                        // Recargar la tabla
                        cargarTabla()
                    }
                    dataRow.addView(buttonEnviar)

                    tableLayout.addView(dataRow)
                }
            }
        }

        // Cargar la tabla al inicio
        cargarTabla()
    }
}
