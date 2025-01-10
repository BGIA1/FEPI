package com.example.logina.Global

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logina.R

class EditarPerfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        val nombreEditText = findViewById<EditText>(R.id.nombreEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val telefonoEditText = findViewById<EditText>(R.id.telefonoEditText)
        val guardarButton = findViewById<Button>(R.id.guardarButton)

        guardarButton.setOnClickListener {
            val nombre = nombreEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val telefono = telefonoEditText.text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Perfil actualizado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
