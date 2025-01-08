package com.example.logina

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

data class Convocatoria(val name: String, val startDate: String, val endDate: String)

class ConvocatoriaActivity : AppCompatActivity() {

    private val convocatoriasList = mutableListOf<Convocatoria>()
    private lateinit var adapter: ConvocatoriaAdapter
    private lateinit var startDate: String
    private lateinit var endDate: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convocatoria)

        val convocatoriaNameEditText = findViewById<EditText>(R.id.convocatoriaNameEditText)
        val startDateButton = findViewById<Button>(R.id.startDateButton)
        val endDateButton = findViewById<Button>(R.id.endDateButton)
        val saveConvocatoriaButton = findViewById<Button>(R.id.saveConvocatoriaButton)
        val recyclerView = findViewById<RecyclerView>(R.id.convocatoriasRecyclerView)

        // Configurar RecyclerView
        adapter = ConvocatoriaAdapter(convocatoriasList) { convocatoria ->
            convocatoriasList.remove(convocatoria)
            adapter.notifyDataSetChanged()

            // Si la convocatoria eliminada es la activa, la quitamos de SharedPreferences
            val sharedPreferences = getSharedPreferences("ConvocatoriaPrefs", MODE_PRIVATE)
            if (sharedPreferences.getString("convocatoriaActiva", null) == convocatoria.name) {
                val editor = sharedPreferences.edit()
                editor.clear() // Limpia los datos de la convocatoria activa
                editor.apply()
                Toast.makeText(this, "Convocatoria activa eliminada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Convocatoria eliminada", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Seleccionar Fecha de Inicio
        startDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                startDate = "$day/${month + 1}/$year"
                startDateButton.text = startDate
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        // Seleccionar Fecha de Fin
        endDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                endDate = "$day/${month + 1}/$year"
                endDateButton.text = endDate
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        // Guardar Convocatoria
        saveConvocatoriaButton.setOnClickListener {
            val name = convocatoriaNameEditText.text.toString().trim()
            if (name.isEmpty() || !::startDate.isInitialized || !::endDate.isInitialized) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Agregar la nueva convocatoria a la lista
            val newConvocatoria = Convocatoria(name, startDate, endDate)
            convocatoriasList.add(newConvocatoria)
            adapter.notifyDataSetChanged()

            // Guardar la convocatoria en SharedPreferences
            val sharedPreferences = getSharedPreferences("ConvocatoriaPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("convocatoriaActiva", name) // Guarda el nombre de la convocatoria activa
            editor.putString("fechaInicio", startDate) // Guarda la fecha de inicio
            editor.putString("fechaFin", endDate) // Guarda la fecha de fin
            editor.apply()

            // Limpiar los campos de entrada
            convocatoriaNameEditText.text.clear()
            startDateButton.text = "Seleccionar fecha de inicio"
            endDateButton.text = "Seleccionar fecha de fin"

            Toast.makeText(this, "Convocatoria guardada exitosamente", Toast.LENGTH_SHORT).show()
        }
    }
}
