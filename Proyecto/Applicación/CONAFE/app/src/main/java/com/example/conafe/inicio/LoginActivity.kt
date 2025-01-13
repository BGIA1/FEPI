package com.example.conafe.inicio
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R
import com.example.conafe.aspirante.AspirantesActivity
import com.example.conafe.coordinador.CoordinadorZonaActivity
import com.example.conafe.delegado.DelegadoEstatalActivity
import com.example.conafe.figuraeducativa.AlumnosActivity
import com.example.logina.login


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.etEmail)
        val passwordEditText = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        // Listener para el botón de inicio de sesión
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            // Llamar a la función:
            login(email,password){ resp ->
                // Comprobar los datos con UserData
                if (resp!="[]") {
                    when (resp) {
                        "Aspirante" -> {
                            runOnUiThread {
                                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            }
                            // Redirigir a PerfilActivity
                            val intent = Intent(this, AspirantesActivity::class.java)
                            startActivity(intent)
                        }
                        "Coordinador" -> {
                            runOnUiThread {
                                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            }
                            // Redirigir a PerfilActivity
                            val intent = Intent(this, CoordinadorZonaActivity::class.java)
                            startActivity(intent)
                        }
                        "Delegado" -> {
                            runOnUiThread {
                                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            }
                            // Redirigir a PerfilActivity
                            val intent = Intent(this, DelegadoEstatalActivity::class.java)
                            startActivity(intent)
                        }
                        "FE" -> {
                            runOnUiThread {
                                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            }
                            // Redirigir a PerfilActivity
                            val intent = Intent(this, AlumnosActivity::class.java)
                            startActivity(intent)
                        }
                        else -> {
                            runOnUiThread {
                                // Opción no reconocida
                                Toast.makeText(this, "Opción no reconocida", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
