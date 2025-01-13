package com.example.conafe.aspirante

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R

class DepositosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tabla_depositos)

        // Referencia al campo de nombre dinámico
        val tvNombre = findViewById<TextView>(R.id.tvNombreDepositos)
        tvNombre.text = "Juan Pérez" // Modifica el nombre dinámicamente

        // Referencia al TableLayout
        val tableLayout = findViewById<TableLayout>(R.id.tableLayoutDepositos)

        // Datos simulados para llenar la tabla
        val registros = listOf(
            listOf("2025-01-01", "$500.00", "123456", "Completado"),
            listOf("2025-01-02", "$300.00", "789012", "Pendiente"),
            listOf("2025-01-03", "$150.00", "345678", "Completado")
        )

        // Agregar encabezados de la tabla
        val headerRow = TableRow(this)
        val headers = listOf("Fecha", "Monto", "Cuenta", "Estatus")
        for (header in headers) {
            val textViewHeader = TextView(this)
            textViewHeader.text = header
            textViewHeader.textSize = 18f
            textViewHeader.setPadding(16, 16, 16, 16)
            textViewHeader.setBackgroundResource(android.R.color.darker_gray)
            textViewHeader.setTextColor(resources.getColor(android.R.color.white))
            headerRow.addView(textViewHeader)
        }
        tableLayout.addView(headerRow)

        // Agregar filas de datos
        for (registro in registros) {
            val dataRow = TableRow(this)
            for (dato in registro) {
                val textViewData = TextView(this)
                textViewData.text = dato
                textViewData.textSize = 16f
                textViewData.setPadding(16, 16, 16, 16)
                dataRow.addView(textViewData)
            }
            tableLayout.addView(dataRow)
        }
    }
}
