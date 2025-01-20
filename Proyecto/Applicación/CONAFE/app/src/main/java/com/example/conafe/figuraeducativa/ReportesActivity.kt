package com.example.conafe.figuraeducativa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R
import com.example.conafe.actualizarRegistro
import com.example.conafe.consulta
import com.example.conafe.database.ReportesDatabaseHelper
import com.example.conafe.supervisor.InicioSupervisorActivity

class ReportesActivity : AppCompatActivity() {

    private lateinit var databaseHelper: ReportesDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportes)

        // Encuentra la Toolbar personalizada y configúrala
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        // Spinner para seleccionar alumnos
        val spinnerAlumnos: Spinner = findViewById(R.id.spinnerAlumnos)

        // Inicializar campos y botón de guardar
        val btnGuardar = findViewById<Button>(R.id.btnGuardarReporte)
        val etComentarios = findViewById<EditText>(R.id.etComentarios)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroupDesempeno)
        // Recuperar datos de sesión
        val idUsuario = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE).getString("id_usuario", null)


        if(idUsuario!=null){// Datos de ejemplo
            consulta(
                "Alumnos", "id, nombre",
                filtros = listOf("id_fe" to idUsuario)
            )
            { resp, exito ->
                if (resp != null && exito) {
                    val alumnos: List<String> = resp.map {
                        val idAlumno = it["id"].toString() as? String ?: ""
                        val nombre = it["nombre"] as? String ?: ""

                        "$idAlumno - $nombre"
                    }
                    runOnUiThread {
                        //Adaptador
                        val adapter =
                            ArrayAdapter(this, android.R.layout.simple_spinner_item, alumnos)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerAlumnos.adapter = adapter
                    }
                }
            }
        }

        spinnerAlumnos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Aquí puedes obtener el ID (posición) y el valor seleccionado
                val selectedItem = parent?.getItemAtPosition(position) as String // Elemento seleccionado
                //idAlumno=selectedItem.split("-")[0]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Lógica si no se selecciona nada
            }
        }

        // Configurar acción del botón "Guardar"
        btnGuardar.setOnClickListener {
            val alumnoSeleccionado = spinnerAlumnos.selectedItem.toString()
            var idAlumno=alumnoSeleccionado.split("-")[0]
            val idOpcion = radioGroup.checkedRadioButtonId
            val desempeno = findViewById<RadioButton>(idOpcion)?.text.toString()
            val comentarios = etComentarios.text.toString()

            // Validar que el formulario esté completo
            if (idOpcion == -1) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                actualizarRegistro("Evaluaciones_Alumnos",
                    camposActualizar =  listOf("desempeno" to desempeno,"comentarios" to comentarios),
                    filtros = listOf("id_alumno" to idAlumno))
                // Mostrar un mensaje temporalmente
                Toast.makeText(
                    this,
                    "Alumno evaluado con éxito: $alumnoSeleccionado - $desempeno",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                // Mostrar un mensaje temporalmente
                Toast.makeText(
                    this,
                    "Error al registrar la evaluación, intente de nuevo más tarde",
                    Toast.LENGTH_LONG
                ).show()
            }

            val intent = Intent(this, FiguraEducativaActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad actual para que no regrese al presionar "atrás"
        }
    }
}
