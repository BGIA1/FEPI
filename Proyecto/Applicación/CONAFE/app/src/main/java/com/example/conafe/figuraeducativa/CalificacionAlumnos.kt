package com.example.conafe.figuraeducativa

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conafe.R
import com.example.conafe.actualizarRegistro
import com.example.conafe.consulta


class CalificacionAlumnos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        // Spinner para seleccionar alumnos
        val spinnerAlumnos: Spinner = findViewById(R.id.spinnerAlumnosMateria)
        var idAlumno=""

        // Recuperar datos de sesión
        val idUsuario = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE).getString("id_usuario", null)

        // Datos de ejemplo
        if(idUsuario!=null){
            consulta(
                "Alumnos", "id, nombre",
                filtros = listOf("id_fe" to idUsuario)
            )
            { resp, exito ->
                if (resp != null && exito) {
                    val alumnos: List<String> = resp.map {
                        val id = it["id"].toString() as? String ?: ""
                        val nombre = it["nombre"] as? String ?: ""
                        "$id - $nombre"
                    }
                    runOnUiThread {
                        val adapter =
                            ArrayAdapter(this, android.R.layout.simple_spinner_item, alumnos)
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerAlumnos.adapter = adapter
                        //Adaptador
                    }
                }
            }
        }

        // Lista de materias
        val materias = listOf(
            Materia("Matemáticas", "", ""),
            Materia("Español", "", ""),
            Materia("Ciencias", "", ""),
            Materia("Historia", "", ""),
            Materia("Geografía", "", "")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rvMaterias)
        recyclerView.layoutManager = LinearLayoutManager(this)

        spinnerAlumnos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Aquí puedes obtener el ID (posición) y el valor seleccionado
                val selectedItem = parent?.getItemAtPosition(position) as String // Elemento seleccionado
                idAlumno=selectedItem.split("-")[0]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Lógica si no se selecciona nada
                }
            }

        // Adaptador con el callback para guardar la calificación
        val adapter = MateriasAdapter(materias) { materia, calificacion ->
            //Registrar calificacion
            actualizarRegistro("Evaluaciones_Alumnos",
            camposActualizar =  listOf("calificacion" to calificacion),
            filtros = listOf("id_alumno" to idAlumno,"materia" to materia.nombre))
            // Aquí puedes manejar el guardado de la calificación
            Toast.makeText(this, "Guardado ${materia.nombre}: $calificacion", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter
    }
}

