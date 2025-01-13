package com.example.conafe.aspirante

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_formulario_perfil)

        // Referencias a los elementos del formulario
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etNumero = findViewById<EditText>(R.id.etNumero)
        val etUbicacion = findViewById<EditText>(R.id.etUbicacion)
        val etNivelCertificado = findViewById<EditText>(R.id.etNivelCertificado)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        // Datos iniciales del formulario
        val datosIniciales = mapOf(
            "Nombre" to "Juan Pérez",
            "Correo" to "juan.perez@example.com",
            "Número" to "1234567890",
            "Ubicación" to "Municipio: Tlalpan, Delegación: Centro",
            "Nivel de Certificado" to "Licenciatura"
        )

        // Asignar valores iniciales a los campos
        etNombre.setText(datosIniciales["Nombre"])
        etCorreo.setText(datosIniciales["Correo"])
        etNumero.setText(datosIniciales["Número"])
        etUbicacion.setText(datosIniciales["Ubicación"])
        etNivelCertificado.setText(datosIniciales["Nivel de Certificado"])

        // Configuración inicial del botón (sin funcionalidad implementada aún)
        btnGuardar.setOnClickListener {
            Toast.makeText(this, "Función no implementada", Toast.LENGTH_SHORT).show()
        }
    }
}
