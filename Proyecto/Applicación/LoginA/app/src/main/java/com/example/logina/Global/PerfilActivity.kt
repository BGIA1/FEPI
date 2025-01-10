package com.example.logina.Global

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logina.R

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val changePhotoButton = findViewById<Button>(R.id.changePhotoButton)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val positionEditText = findViewById<EditText>(R.id.positionEditText)
        val assignmentEditText = findViewById<EditText>(R.id.assignmentEditText)
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)

        // Botón para cambiar la foto de perfil
        changePhotoButton.setOnClickListener {
            Toast.makeText(this, "Función para cambiar foto de perfil en desarrollo", Toast.LENGTH_SHORT).show()
        }

        // Botón para guardar los cambios
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val position = positionEditText.text.toString().trim()
            val assignment = assignmentEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()

            if (name.isEmpty() || position.isEmpty() || assignment.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
            // Aquí puedes guardar los datos en una base de datos o SharedPreferences
        }

        // Botón para cancelar
        cancelButton.setOnClickListener {
            finish() // Regresa a la actividad anterior
        }
    }
}
