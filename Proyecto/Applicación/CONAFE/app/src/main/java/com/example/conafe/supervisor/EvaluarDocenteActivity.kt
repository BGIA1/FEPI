package com.example.conafe.supervisor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.conafe.R
import com.example.conafe.actualizarRegistro
import com.example.conafe.consulta
import com.example.conafe.consultaJSON
import com.example.conafe.figuraeducativa.AlumnosAdapter
import com.example.conafe.supabase
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray

class EvaluarDocenteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluar_docente)

        // Spinner para Figuras Educativas
        val spinnerFiguras: Spinner = findViewById(R.id.spinnerFiguras)
        // Spinner para desempeño
        val spinnerDesempeno: Spinner = findViewById(R.id.spinnerDesempeno)

        // Configurar la toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Evaluar Docente"
            setDisplayHomeAsUpEnabled(true)
        }

        // Opciones de desempeño
        val desempenos = listOf(
            "POR DEBAJO DE LAS EXPECTATIVAS",
            "CUMPLIENDO CON LAS EXPECTATIVAS",
            "SUPERANDO LAS EXPECTATIVAS"
        )

        val adapterDesempeno = ArrayAdapter(this, android.R.layout.simple_spinner_item, desempenos)
        adapterDesempeno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDesempeno.adapter = adapterDesempeno

        // Recuperar datos de sesión
        val idUsuario = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE).getString("id_usuario", null)
        val figurasEducativas = mutableListOf("")
        if(idUsuario!=null)
        {
            consultaJSON(
                "Figuras_Educativas",
                """
                id_usuario,
                user_data:Usuarios!id_usuario 
                (
                    nombre
                )
                """,
                filtros = listOf("id_supervisor" to idUsuario)) { resp,exito ->
                if(exito && resp!=null){
                    val jsonArray = JSONArray(resp)
                    //println(jsonArray)

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val userData = jsonObject.getJSONObject("user_data")
                        figurasEducativas.add(jsonObject.getString("id_usuario")+"-"+userData.getString("nombre"))
                    }
                    runOnUiThread {
                        println(figurasEducativas)
                        val adapterFiguras =
                            ArrayAdapter(this, android.R.layout.simple_spinner_item, figurasEducativas.subList(1,figurasEducativas.size))
                        adapterFiguras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerFiguras.adapter = adapterFiguras
                    }
                }
            }
        }

        // Botón para guardar la evaluación
        val btnGuardar: Button = findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener {
            val figuraSeleccionada = spinnerFiguras.selectedItem.toString().split("-").first()
            val desempenoSeleccionado = spinnerDesempeno.selectedItem.toString()

            try {
                actualizarRegistro("Figuras_Educativas",
                    camposActualizar =  listOf("evaluacion_desempeño" to desempenoSeleccionado),
                    filtros = listOf("id_usuario" to figuraSeleccionada))
                // Mostrar un mensaje temporalmente
                Toast.makeText(
                    this,
                    "Evaluación completada con éxito: $figuraSeleccionada - $desempenoSeleccionado",
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

            val intent = Intent(this, InicioSupervisorActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad actual para que no regrese al presionar "atrás"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
