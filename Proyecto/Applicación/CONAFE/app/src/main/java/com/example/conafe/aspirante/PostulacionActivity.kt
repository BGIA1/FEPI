package com.example.conafe.aspirante

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R

class PostulacionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_postulacion)

        // Título
        val tvTitulo = findViewById<TextView>(R.id.tvTituloPostulacion)
        tvTitulo.text = "Postularse para Figura Educativa"

        // Botón de postulación
        val btnPostularse = findViewById<Button>(R.id.btnPostularse)

        // Variable para la validación del certificado
        val tieneCertificado = "no" // Cambiar a "no" para probar la validación negativa

        btnPostularse.setOnClickListener {
            if (tieneCertificado == "si") {
                Toast.makeText(this, "Postulación exitosa", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No se cuenta con certificados para la postulación", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
