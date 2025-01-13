package com.example.conafe.supervisor

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.conafe.R

class EvaluarDocenteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluar_docente)

        // Configurar la toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Evaluar Docente"
            setDisplayHomeAsUpEnabled(true)
        }

        // Lista de nombres de Figuras Educativas
        val figurasEducativas = listOf("Figura 1", "Figura 2", "Figura 3", "Figura 4")

        // Opciones de desempeño
        val desempenos = listOf(
            "POR DEBAJO DE LAS EXPECTATIVAS",
            "CUMPLIENDO CON LAS EXPECTATIVAS",
            "SUPERANDO LAS EXPECTATIVAS"
        )

        // Spinner para Figuras Educativas
        val spinnerFiguras: Spinner = findViewById(R.id.spinnerFiguras)
        val adapterFiguras = ArrayAdapter(this, android.R.layout.simple_spinner_item, figurasEducativas)
        adapterFiguras.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFiguras.adapter = adapterFiguras

        // Spinner para desempeño
        val spinnerDesempeno: Spinner = findViewById(R.id.spinnerDesempeno)
        val adapterDesempeno = ArrayAdapter(this, android.R.layout.simple_spinner_item, desempenos)
        adapterDesempeno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDesempeno.adapter = adapterDesempeno

        // Botón para guardar la evaluación
        val btnGuardar: Button = findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener {
            val figuraSeleccionada = spinnerFiguras.selectedItem.toString()
            val desempenoSeleccionado = spinnerDesempeno.selectedItem.toString()
            // Mostrar un mensaje temporalmente
            Toast.makeText(
                this,
                "Guardado: $figuraSeleccionada - $desempenoSeleccionado",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
