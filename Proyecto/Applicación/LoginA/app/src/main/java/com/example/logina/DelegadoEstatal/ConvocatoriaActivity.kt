package com.example.logina.DelegadoEstatal

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logina.DataBase.Convocatoria
import com.example.logina.DataBase.ConvocatoriasTable
import com.example.logina.DataBase.DatabaseConfig
import com.example.logina.R
import java.util.*

class ConvocatoriaActivity : AppCompatActivity() {

    private val convocatoriasList = mutableListOf<Convocatoria>()
    private lateinit var adapter: ConvocatoriaAdapter
    private lateinit var startDate: String
    private lateinit var endDate: String
    private lateinit var dbConfig: DatabaseConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convocatoria)

        // Inicializar DatabaseConfig
        dbConfig = DatabaseConfig(this)

        // Cargar convocatorias desde la base de datos
        loadConvocatorias()

        val convocatoriaNameEditText = findViewById<EditText>(R.id.convocatoriaNameEditText)
        val startDateButton = findViewById<Button>(R.id.startDateButton)
        val endDateButton = findViewById<Button>(R.id.endDateButton)
        val saveConvocatoriaButton = findViewById<Button>(R.id.saveConvocatoriaButton)
        val recyclerView = findViewById<RecyclerView>(R.id.convocatoriasRecyclerView)

        adapter = ConvocatoriaAdapter(convocatoriasList) { convocatoria ->
            val db = dbConfig.writableDatabase
            val rowsDeleted = ConvocatoriasTable.deleteConvocatoria(
                db,
                convocatoria.name,
                convocatoria.startDate,
                convocatoria.endDate
            )
            if (rowsDeleted > 0) {
                convocatoriasList.remove(convocatoria)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Convocatoria eliminada exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al eliminar la convocatoria", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        startDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                startDate = "$day/${month + 1}/$year"
                startDateButton.text = startDate
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        endDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                endDate = "$day/${month + 1}/$year"
                endDateButton.text = endDate
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        saveConvocatoriaButton.setOnClickListener {
            val name = convocatoriaNameEditText.text.toString().trim()
            if (name.isEmpty() || !::startDate.isInitialized || !::endDate.isInitialized) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbConfig.writableDatabase
            val result = ConvocatoriasTable.addConvocatoria(db, name, startDate, endDate)
            if (result != -1L) {
                val newConvocatoria = Convocatoria(name, startDate, endDate)
                convocatoriasList.add(newConvocatoria)
                adapter.notifyDataSetChanged()

                convocatoriaNameEditText.text.clear()
                startDateButton.text = "Seleccionar fecha de inicio"
                endDateButton.text = "Seleccionar fecha de fin"

                Toast.makeText(this, "Convocatoria guardada exitosamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al guardar la convocatoria", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadConvocatorias() {
        convocatoriasList.clear()
        val db = dbConfig.readableDatabase
        convocatoriasList.addAll(ConvocatoriasTable.getAllConvocatorias(db))
    }
}
