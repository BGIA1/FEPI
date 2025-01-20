package com.example.conafe.supervisor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conafe.R
import com.example.conafe.actualizarRegistro
import com.example.conafe.consulta


class RecursosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recursos)

        // Configurar la barra de herramientas
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Acción del botón aceptar
        val btnAceptar: Button = findViewById(R.id.btnAceptar)

        // Configurar la lista de alumnos
        val recyclerView = findViewById<RecyclerView>(R.id.tablaRecursos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener las SharedPreferences
        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Recuperar datos de sesión
        val idUsuario = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE).getString("id_usuario", null)

        var idEntrega=""
        var statusEntrega=""

        //Consultar todas las FE
        if (idUsuario!=null){
            consulta(
                "Supervisores_Modulo", "zona_asignada",
                filtros = listOf("id_usuario" to idUsuario)
            )
            { resp, exito ->
                if (resp != null && exito) {
                    val zonaAsignada = resp.map {
                        it["zona_asignada"].toString() as? String ?: ""
                    }.first()
                    println(zonaAsignada)

                    getListaRecursos(zonaAsignada) { recursos ->
                        println(recursos)
                        runOnUiThread {
                            idEntrega = recursos[0]
                            statusEntrega = recursos[1]
                            btnAceptar.visibility =
                                if (statusEntrega == "RECIBIDO") View.GONE else View.VISIBLE
                            recyclerView.adapter =
                                RecursosAdapter(recursos.subList(2, recursos.size))
                        }
                    }
                }
            }
        }

        // Personalizar la barra de acción
        supportActionBar?.apply {
            title = "Recursos"
            setDisplayHomeAsUpEnabled(true)
        }

        btnAceptar.setOnClickListener {
            try {
                actualizarRegistro("historial_padron",
                    camposActualizar =  listOf("estatus" to "RECIBIDO"),
                    filtros = listOf("id" to idEntrega))

                // Mostrar un mensaje temporalmente
                Toast.makeText(
                    this,
                    "Recepción de recursos confirmada con éxito",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                // Mostrar un mensaje temporalmente
                Toast.makeText(
                    this,
                    "Error al confirmar la recepción de recursos, intente de nuevo más tarde",
                    Toast.LENGTH_LONG
                ).show()
            }

            val intent = Intent(this, InicioSupervisorActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad actual para que no regrese al presionar "atrás"
        }
    }

    fun getListaRecursos(estado: String, callback: (List<String>) -> Unit){
            consulta("historial_padron", "id,Gorra,Playera_tipo_polo,Mochila,Chamarra_polar,Botas,Chamarra_ligera,Tenis,Bolsa_de_Dormir,estatus",
                filtros = listOf("Estado" to estado))
            { resp, exito ->
                if (resp != null && exito) {
                    val recursos = resp.map {
                        val id = it["id"].toString() as? String ?: ""
                        val status = it["estatus"].toString() as? String ?: ""
                        val gorra = it["Gorra"].toString() as? String ?: ""
                        val playeraPolo = it["Playera_tipo_polo"] as? String ?: ""
                        val mochila = it["Mochila"] as? String ?: ""
                        val chamarraPolar = it["Chamarra_polar"] as? String ?: ""
                        val botas = it["Botas"] as? String ?: ""
                        val chamarraLigera = it["Chamarra_ligera"] as? String ?: ""
                        val tenis = it["Tenis"] as? String ?: ""
                        val bolsaDormir = it["Bolsa_de_Dormir"] as? String ?: ""
                        """
                    |$id
                    |$status
                    |Gorras: $gorra
                    |Playeras tipo polo: $playeraPolo
                    |Mochilas: $mochila
                    |Chamarras tipo polar: $chamarraPolar 
                    |Botas: $botas 
                    |Chamarra ligera: $chamarraLigera
                    |Tenis: $tenis
                    |Bolsas para dormir: $bolsaDormir
                """.trimMargin()
                    }.last().split("\n")
                    callback(recursos)
                }
            }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
