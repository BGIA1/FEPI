package com.example.logina

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistroConvocatoriaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_convocatoria)

        val convocatoriaNombreTextView = findViewById<TextView>(R.id.convocatoriaNombreTextView)
        val registroNombreEditText = findViewById<EditText>(R.id.registroNombreEditText)
        val registroEmailEditText = findViewById<EditText>(R.id.registroEmailEditText)
        val registroTelefonoEditText = findViewById<EditText>(R.id.registroTelefonoEditText)
        val subirDocumentacionButton = findViewById<Button>(R.id.subirDocumentacionButton)
        val enviarRegistroButton = findViewById<Button>(R.id.enviarRegistroButton)

        // Obtener el nombre de la convocatoria activa
        val convocatoriaNombre = intent.getStringExtra("convocatoriaNombre")
        convocatoriaNombreTextView.text = "Convocatoria Activa: $convocatoriaNombre"

        // Subir Documentación (simulado)
        subirDocumentacionButton.setOnClickListener {
            Toast.makeText(this, "Función para subir documentación en desarrollo", Toast.LENGTH_SHORT).show()
        }

        // Enviar Registro
        enviarRegistroButton.setOnClickListener {
            val nombre = registroNombreEditText.text.toString().trim()
            val email = registroEmailEditText.text.toString().trim()
            val telefono = registroTelefonoEditText.text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simulamos el registro (puedes guardarlo en una base de datos aquí)
            Toast.makeText(this, "Registro enviado exitosamente", Toast.LENGTH_SHORT).show()
            finish() // Cierra la actividad
        }
    }
}
