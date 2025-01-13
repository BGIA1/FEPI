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

class AsignacionDocenteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_asignacion_docente)

        // Referencia al TableLayout
        val tableLayout = findViewById<TableLayout>(R.id.tableLayoutAsignacionDocente)

        // Datos simulados
        val registros = mutableListOf("Grupo A", "Grupo B")
        val estado = mutableListOf(0, 0) // Estados iniciales: 0 no asignado, 1 asignado
        val opcionesAsignacion = listOf("Docente A", "Docente B", "Docente C")

        // Método para cargar la tabla
        fun cargarTabla() {
            tableLayout.removeAllViews()

            // Agregar encabezados de la tabla
            val headerRow = TableRow(this)
            val headers = listOf("Grupo", "Docente", "Acción")
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
            for ((index, grupo) in registros.withIndex()) {
                if (estado[index] == 0) { // Solo mostrar grupos no asignados
                    val dataRow = TableRow(this)

                    // Columna: Nombre del grupo
                    val textViewNombre = TextView(this)
                    textViewNombre.text = grupo
                    textViewNombre.textSize = 16f
                    textViewNombre.setPadding(16, 16, 16, 16)
                    dataRow.addView(textViewNombre)

                    // Columna: Menú desplegable
                    val spinner = Spinner(this)
                    val adapter = android.widget.ArrayAdapter(
                        this,
                        android.R.layout.simple_spinner_item,
                        opcionesAsignacion
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter
                    dataRow.addView(spinner)

                    // Columna: Botón Asignar
                    val buttonAsignar = Button(this)
                    buttonAsignar.text = "Asignar"
                    buttonAsignar.setOnClickListener {
                        val opcionSeleccionada = spinner.selectedItem.toString()
                        Toast.makeText(this, "$grupo asignado a $opcionSeleccionada", Toast.LENGTH_SHORT).show()

                        // Actualizar el estado del grupo a asignado
                        estado[index] = 1

                        // Recargar la tabla
                        cargarTabla()
                    }
                    dataRow.addView(buttonAsignar)

                    tableLayout.addView(dataRow)
                }
            }
        }

        // Cargar la tabla al inicio
        cargarTabla()
    }
}
