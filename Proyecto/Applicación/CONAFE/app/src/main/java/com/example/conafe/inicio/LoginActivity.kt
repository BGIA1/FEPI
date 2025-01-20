package com.example.conafe.inicio
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R
import com.example.conafe.actualizarRegistro
import com.example.conafe.consulta


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usuarioEditText = findViewById<EditText>(R.id.etUsuario)
        val passwordEditText = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        // Listener para el botón de inicio de sesión
        loginButton.setOnClickListener {
            val usuarioId = usuarioEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (usuarioId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Llamar a la función:
            consulta("Usuarios","tipo_usuario",
                filtros = listOf("id_usuario" to usuarioId,"contraseña" to password))
            {resp,exito ->
                if(exito){// Comprobar los datos con UserData
                    var tipoUsuario = resp?.map {
                        val tipoUsuario = it["tipo_usuario"].toString() as? String ?: ""
                        tipoUsuario
                    }?.first()

                    if (tipoUsuario != "" && tipoUsuario!=null) {
                        iniciarSesion(usuarioId, tipoUsuario)
                        //Invocar la función de la clase utilitaria
                        IntentUtils.cargarMenuUsuario(usuarioId, tipoUsuario, this)
                    } else {
                        runOnUiThread {
                            Toast.makeText(
                                this,
                                "Correo o contraseña incorrectos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                else
                {
                    runOnUiThread {
                        Toast.makeText(
                            this,
                            "Correo o contraseña incorrectos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    fun iniciarSesion(idUsuario:String,tipoUsuario:String)
    {
        // Obtener el editor de SharedPreferences
        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Guardar datos de sesión
        editor.putString("id_usuario", idUsuario)
        editor.putString("tipoUsuario", tipoUsuario)
        editor.apply() // Aplicar cambios

        runOnUiThread {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
        }
    }
}