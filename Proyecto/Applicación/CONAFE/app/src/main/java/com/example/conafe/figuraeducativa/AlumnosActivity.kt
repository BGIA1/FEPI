package com.example.conafe.figuraeducativa

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conafe.R
import com.example.conafe.consulta

class AlumnosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumnos)

        // Configurar la lista de alumnos
        val recyclerView = findViewById<RecyclerView>(R.id.rvAlumnos)
        recyclerView.layoutManager = LinearLayoutManager(this)

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
                        println(alumnos)
                        //Adaptador
                        recyclerView.adapter = AlumnosAdapter(alumnos)
                    }
                }
            }
        }
    }
}

