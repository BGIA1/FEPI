package com.example.conafe.coordinador

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R
import java.util.Calendar

class AsignarVisitaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_asignar_visita)

        // Referencia al TableLayout
        val tableLayout = findViewById<TableLayout>(R.id.tableLayoutAsignarVisita)

        // Datos simulados
        val registros = mutableListOf("Juan Pérez", "Ana López")
        val estado = mutableListOf(0, 0) // Estados iniciales: 0 no asignado, 1 asignado

        // Método para cargar la tabla
        fun cargarTabla() {
            tableLayout.removeAllViews()

            // Agregar encabezados de la tabla
            val headerRow = TableRow(this)
            val headers = listOf("Nombre", "Fecha", "Acción")
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
                if (estado[index] == 0) { // Solo mostrar registros no asignados
                    val dataRow = TableRow(this)

                    // Columna: Nombre
                    val textViewNombre = TextView(this)
                    textViewNombre.text = nombre
                    textViewNombre.textSize = 16f
                    textViewNombre.setPadding(16, 16, 16, 16)
                    dataRow.addView(textViewNombre)

                    // Columna: Fecha
                    val textViewFecha = TextView(this)
                    textViewFecha.text = "Seleccionar Fecha"
                    textViewFecha.textSize = 16f
                    textViewFecha.setPadding(16, 16, 16, 16)
                    textViewFecha.setOnClickListener {
                        val calendar = Calendar.getInstance()
                        val datePicker = DatePickerDialog(this, { _: DatePicker, year: Int, month: Int, day: Int ->
                            textViewFecha.text = "$day/${month + 1}/$year"
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                        datePicker.show()
                    }
                    dataRow.addView(textViewFecha)

                    // Columna: Botón Asignar
                    val buttonAsignar = Button(this)
                    buttonAsignar.text = "Asignar"
                    buttonAsignar.setOnClickListener {
                        if (textViewFecha.text == "Seleccionar Fecha") {
                            Toast.makeText(this, "Por favor seleccione una fecha", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "$nombre asignado para la visita el ${textViewFecha.text}", Toast.LENGTH_SHORT).show()

                            // Actualizar el estado del registro a asignado
                            estado[index] = 1

                            // Recargar la tabla
                            cargarTabla()
                        }
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
