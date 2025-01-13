package com.example.conafe.inicio
import com.example.conafe.supervisor.InicioSupervisorActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R
import com.example.conafe.aspirante.AlumnoActivity
import com.example.conafe.coordinador.CoordinadorZonaActivity
import com.example.conafe.figuraeducativa.FiguraEducativaActivity



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

            when {
                email == "bot@figuraeducativa.com" && password == "123456" -> {
                    // Redirigir a la pantalla de Figura Educativa
                    val intent = Intent(this, FiguraEducativaActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                email == "bot@supervisormodulo.com" && password == "supervisor123" -> {
                    // Redirigir a la pantalla del Supervisor de Módulo
                    val intent = Intent(this, InicioSupervisorActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                else -> {
                    // Mostrar mensaje de error
                    Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    // Temporal para mostrar pantallas no validadas
                    val intent = Intent(this, CoordinadorZonaActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
