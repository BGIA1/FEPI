package com.example.logina

import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BecarioStatusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_becario_status)

        val tableLayout = findViewById<TableLayout>(R.id.statusTableLayout)

        val headers = listOf("Aceptado", "Vigente", "Zona", "Módulo")
        val headerRow = TableRow(this)
        headers.forEach { header ->
            val textView = TextView(this).apply {
                text = header
                textSize = 16f
                setPadding(16, 16, 16, 16)
            }
            headerRow.addView(textView)
        }
        tableLayout.addView(headerRow)

        val data = listOf(
            listOf("Sí", "Sí", "Zona A", "Módulo 1"),
            listOf("No", "No", "Zona B", "Módulo 2")
        )
        data.forEach { row ->
            val tableRow = TableRow(this)
            row.forEach { cell ->
                val cellTextView = TextView(this).apply {
                    text = cell
                    textSize = 14f
                    setPadding(16, 16, 16, 16)
                }
                tableRow.addView(cellTextView)
            }
            tableLayout.addView(tableRow)
        }
    }
}
