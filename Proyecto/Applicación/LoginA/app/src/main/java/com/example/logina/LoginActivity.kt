package com.example.logina

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // Usuarios predeterminados
    private val defaultUsers = mapOf(
        "usuario@gmail.com" to "123456",
        "bot@becario.com" to "123456",
        "bot@coordinadorzona.com" to "123456",
        "bot@delegadoestatal.com" to "123456"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificar credenciales
            when {
                email == "bot@delegadoestatal.com" && password == "123456" -> {
                    Toast.makeText(this, "Inicio de sesión como Delegado Estatal", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, DelegadoActivity::class.java)
                    startActivity(intent)
                }
                email == "bot@coordinadorzona.com" && password == "123456" -> {
                    Toast.makeText(this, "Inicio de sesión como Coordinador de Zona", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, CoordinadorActivity::class.java)
                    startActivity(intent)
                }
                email == "bot@supervisormodulo.com" && password == "123456" -> {
                    // Redirigir a SupervisorActivity si es Supervisor de Módulo
                    Toast.makeText(this, "Inicio de sesión como Supervisor de Módulo", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, SupervisorActivity::class.java)
                    startActivity(intent)
                }
                email == "bot@becario.com" && password == "123456" -> {
                    // Redirigir a BecarioMenuActivity si es Becario
                    Toast.makeText(this, "Inicio de sesión como Becario", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, PantallaInicioBecario::class.java)
                    startActivity(intent)
                }
                defaultUsers[email] == password -> {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
