package com.example.conafe.figuraeducativa

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R
import com.example.conafe.database.ReportesDatabaseHelper

class ReportesActivity : AppCompatActivity() {

    private lateinit var databaseHelper: ReportesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportes)

        // Encuentra la Toolbar personalizada y configúrala
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Inicializar la base de datos
        databaseHelper = ReportesDatabaseHelper(this)

        // Spinner para seleccionar alumnos
        val spinnerAlumnos: Spinner = findViewById(R.id.spinnerAlumnos)
        val alumnos = listOf("Juan Pérez", "Ana Gómez", "Carlos López", "María Hernández", "Pedro Martínez")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, alumnos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAlumnos.adapter = adapter

        // Inicializar campos y botón de guardar
        val btnGuardar = findViewById<Button>(R.id.btnGuardarReporte)
        val etComentarios = findViewById<EditText>(R.id.etComentarios)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroupDesempeno)

        // Configurar acción del botón "Guardar"
        btnGuardar.setOnClickListener {
            val alumnoSeleccionado = spinnerAlumnos.selectedItem.toString()
            val idOpcion = radioGroup.checkedRadioButtonId
            val desempeno = findViewById<RadioButton>(idOpcion)?.text.toString()
            val comentarios = etComentarios.text.toString()

            // Validar que el formulario esté completo
            if (idOpcion == -1 || comentarios.isBlank()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guardar en la base de datos
            val resultado = databaseHelper.guardarReporte(alumnoSeleccionado, desempeno, comentarios)
            if (resultado != -1L) {
                Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                // Limpiar campos después de guardar
                radioGroup.clearCheck()
                etComentarios.text.clear()
            } else {
                Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
