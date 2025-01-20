package com.example.conafe

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.aspirante.AspirantesActivity
import com.example.conafe.inicio.MainActivity
import org.json.JSONArray

class ProfileActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_formulario_perfil)

        // Referencias a los elementos del formulario
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etNumero = findViewById<EditText>(R.id.etNumero)
        val etUbicacion = findViewById<EditText>(R.id.etUbicacion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        // Recuperar datos de sesión
        val idUsuario = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE).getString("id_usuario", null)

        if(idUsuario!=null)
        {
            consultaJSON(
                "Usuarios",
                """
                nombre,correo,telefono,estado,municipio
                """.trimIndent(),
                filtros = listOf("id_usuario" to idUsuario)) { resp,exito ->
                if(exito && resp!=null){
                    val jsonArray = JSONArray(resp)
                    println(jsonArray)

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        runOnUiThread {
                            etNombre.setText(jsonObject.getString("nombre"))
                            etCorreo.setText(jsonObject.getString("correo"))
                            etNumero.setText(jsonObject.getString("telefono"))
                            etUbicacion.setText(jsonObject.getString("estado")+", "+jsonObject.getString("municipio"))
                        }
                    }
                }
            }
        }

        // Configuración inicial del botón (sin funcionalidad implementada aún)
        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            var correo=etCorreo.text.toString()
            val numero = etNumero.text.toString()

            // Validar que el formulario esté completo
            if (nombre=="" || correo=="" || numero=="") {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                if(idUsuario!=null)
                {
                    actualizarRegistro("Usuarios",
                        camposActualizar =  listOf("nombre" to nombre,"correo" to correo,"telefono" to numero),
                        filtros = listOf("id_usuario" to idUsuario))
                }
                // Mostrar un mensaje temporalmente
                Toast.makeText(
                    this,
                    "Datos actualizados con éxito",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                // Mostrar un mensaje temporalmente
                Toast.makeText(
                    this,
                    "Error al actualizar los datos",
                    Toast.LENGTH_LONG
                ).show()
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad actual para que no regrese al presionar "atrás"
        }
    }
}
