package com.example.logina

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PostularseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postularse)

        val becaSpinner = findViewById<Spinner>(R.id.becaSpinner)
        val postularButton = findViewById<Button>(R.id.postularButton)
        val statusTextView = findViewById<TextView>(R.id.statusTextView)

        val becas = listOf("Beca 1", "Beca 2", "Beca 3")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, becas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        becaSpinner.adapter = adapter

        postularButton.setOnClickListener {
            val selectedBeca = becaSpinner.selectedItem.toString()
            statusTextView.text = "Postulación: $selectedBeca (En revisión)"
        }
    }
}
