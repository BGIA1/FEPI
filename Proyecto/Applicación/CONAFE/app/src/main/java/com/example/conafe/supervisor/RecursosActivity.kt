package com.example.conafe.supervisor

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.conafe.R

class RecursosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recursos)

        // Configurar la barra de herramientas
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Personalizar la barra de acción
        supportActionBar?.apply {
            title = "Recursos"
            setDisplayHomeAsUpEnabled(true)
        }

        // Acción del botón aceptar
        val btnAceptar: Button = findViewById(R.id.btnAceptar)
        btnAceptar.setOnClickListener {
            finish() // Cierra la actividad
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
