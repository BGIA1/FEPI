package com.example.logina

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logina.DataBase.DatabaseConfig
import com.example.logina.DataBase.UsuariosTable

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbConfig: DatabaseConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        dbConfig = DatabaseConfig(this)

        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val surnameEditText = findViewById<EditText>(R.id.surnameEditText)
        val birthDateEditText = findViewById<EditText>(R.id.birthDateEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val surname = surnameEditText.text.toString().trim()
            val birthDate = birthDateEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            // Validación de campos obligatorios
            if (name.isEmpty() || surname.isEmpty() || birthDate.isEmpty() ||
                email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
            ) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación del formato del correo electrónico
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Por favor, ingresa un correo válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación del número de celular (10 dígitos)
            if (!phone.matches(Regex("\\d{10}"))) {
                Toast.makeText(this, "Por favor, ingresa un número de celular válido (10 dígitos)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación de la coincidencia de contraseñas
            if (password != confirmPassword) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbConfig.readableDatabase

            // Validación de la existencia previa del correo
            val usuarios = UsuariosTable.getAllUsuarios(db)
            if (usuarios.any { it.correo == email }) {
                Toast.makeText(this, "El correo ya está registrado", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Registro del nuevo usuario
            val writableDb = dbConfig.writableDatabase
            val result = UsuariosTable.addUsuario(
                db = writableDb,
                nombre = name,
                apellidos = surname,
                correo = email,
                fechaNacimiento = birthDate,
                celular = phone,
                contrasena = password,
                tipoCuenta = "usuario" // Tipo de cuenta predeterminado
            )

            if (result != -1L) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                finish() // Regresa a la pantalla anterior
            } else {
                Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
