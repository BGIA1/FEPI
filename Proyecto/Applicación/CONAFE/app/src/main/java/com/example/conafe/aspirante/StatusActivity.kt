package com.example.conafe.aspirante

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.conafe.R

class StatusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tabla_status)

        // Declaración de valores para la tabla
        val estatus = "Si"
        val municipio = "CDMX"
        val delegacion = "GTAM"

        // Referencias a los TextViews en el diseño
        val tvEstatus = findViewById<TextView>(R.id.tvEstatus)
        val tvMunicipio = findViewById<TextView>(R.id.tvMunicipio)
        val tvDelegacion = findViewById<TextView>(R.id.tvDelegacion)

        // Asignar valores a los TextViews
        tvEstatus.text = estatus
        tvMunicipio.text = municipio
        tvDelegacion.text = delegacion
    }
}
