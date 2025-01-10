package com.example.logina.SupervisorModulo

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.logina.R

class ReportesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportes)

        // Configuración de las secciones
        val layout = findViewById<LinearLayout>(R.id.reportesLayout)

        // Sección de Reportes de Desempeño de Docentes
        val docentesHeader = TextView(this).apply {
            text = "Reportes de Desempeño de Docentes"
            textSize = 18f
            setPadding(16, 16, 16, 8)
        }
        val docentesContent = TextView(this).apply {
            text = "Aquí se listarán los reportes de desempeño de los docentes."
            textSize = 16f
            setPadding(16, 8, 16, 16)
        }

        // Sección de Reportes de Becarios
        val becariosHeader = TextView(this).apply {
            text = "Reportes de Becarios"
            textSize = 18f
            setPadding(16, 16, 16, 8)
        }
        val becariosContent = TextView(this).apply {
            text = "Aquí se listarán los reportes de los becarios."
            textSize = 16f
            setPadding(16, 8, 16, 16)
        }

        // Agregar las secciones al layout
        layout.addView(docentesHeader)
        layout.addView(docentesContent)
        layout.addView(becariosHeader)
        layout.addView(becariosContent)
    }
}
