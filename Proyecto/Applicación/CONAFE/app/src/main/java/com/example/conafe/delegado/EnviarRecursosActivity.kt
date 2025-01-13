package com.example.conafe.delegado

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R

class EnviarRecursosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_enviar_recursos)

        // Referencias a los elementos del formulario
        val spinnerRecurso = findViewById<Spinner>(R.id.spinnerRecurso)
        val spinnerZona = findViewById<Spinner>(R.id.spinnerZona)
        val editTextCantidad = findViewById<EditText>(R.id.editTextCantidad)
        val buttonEnviar = findViewById<Button>(R.id.buttonEnviar)

        // Opciones para los menús desplegables
        val opcionesRecurso = listOf("Recurso A", "Recurso B", "Recurso C")
        val opcionesZona = listOf("Zona 1", "Zona 2", "Zona 3")

        // Configurar adaptadores para los Spinners
        val recursoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesRecurso)
        recursoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRecurso.adapter = recursoAdapter

        val zonaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesZona)
        zonaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerZona.adapter = zonaAdapter

        // Configurar acción del botón Enviar
        buttonEnviar.setOnClickListener {
            val recursoSeleccionado = spinnerRecurso.selectedItem.toString()
            val zonaSeleccionada = spinnerZona.selectedItem.toString()
            val cantidad = editTextCantidad.text.toString()

            if (cantidad.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese una cantidad", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Recurso: $recursoSeleccionado, Zona: $zonaSeleccionada, Cantidad: $cantidad enviado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
