package com.example.conafe.coordinador

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R

class PromocionarAlumnosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_promocionar_alumnos)

        // Referencia al TableLayout
        val tableLayout = findViewById<TableLayout>(R.id.tableLayoutPromocionarAlumnos)

        // Datos simulados
        val registros = mutableListOf("Juan Pérez", "Ana López")
        val estado = mutableListOf(0, 0) // Estados iniciales: 0 no promovido, 1 promovido
        val calificaciones = listOf(85, 92) // Calificaciones correspondientes

        // Método para cargar la tabla
        fun cargarTabla() {
            tableLayout.removeAllViews()

            // Agregar encabezados de la tabla
            val headerRow = TableRow(this)
            val headers = listOf("Nombre", "Calificación", "Acción")
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
            for ((index, nombre) in registros.withIndex()) {
                if (estado[index] == 0) { // Solo mostrar alumnos no promovidos
                    val dataRow = TableRow(this)

                    // Columna: Nombre
                    val textViewNombre = TextView(this)
                    textViewNombre.text = nombre
                    textViewNombre.textSize = 16f
                    textViewNombre.setPadding(16, 16, 16, 16)
                    dataRow.addView(textViewNombre)

                    // Columna: Calificación
                    val textViewCalificacion = TextView(this)
                    textViewCalificacion.text = calificaciones[index].toString()
                    textViewCalificacion.textSize = 16f
                    textViewCalificacion.setPadding(16, 16, 16, 16)
                    dataRow.addView(textViewCalificacion)

                    // Columna: Botón Promover
                    val buttonPromover = Button(this)
                    buttonPromover.text = "Promover"
                    buttonPromover.setOnClickListener {
                        Toast.makeText(this, "$nombre promovido", Toast.LENGTH_SHORT).show()

                        // Actualizar el estado del alumno a promovido
                        estado[index] = 1

                        // Recargar la tabla
                        cargarTabla()
                    }
                    dataRow.addView(buttonPromover)

                    tableLayout.addView(dataRow)
                }
            }
        }

        // Cargar la tabla al inicio
        cargarTabla()
    }
}
