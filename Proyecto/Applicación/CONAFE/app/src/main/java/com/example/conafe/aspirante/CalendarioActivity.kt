package com.example.conafe.aspirante

import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R

class CalendarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_calendario)

        // Referencia al calendario
        val calendario = findViewById<CalendarView>(R.id.calendarView)

        // Fechas importantes (simuladas)
        val fechasEntrega = listOf("2025-01-15", "2025-01-20", "2025-01-25")

        // Configurar la acción al seleccionar una fecha
        calendario.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val fechaSeleccionada = "$year-${month + 1}-$dayOfMonth"
            if (fechasEntrega.contains(fechaSeleccionada)) {
                Toast.makeText(this, "Entrega programada para esta fecha", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No hay entregas programadas para esta fecha", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
