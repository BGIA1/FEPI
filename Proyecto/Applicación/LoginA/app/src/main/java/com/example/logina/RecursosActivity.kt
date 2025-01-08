package com.example.logina

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RecursosActivity : AppCompatActivity() {

    private val recursosList = mutableListOf<Recurso>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recursos)

        val recyclerView = findViewById<RecyclerView>(R.id.recursosRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = RecursosAdapter(recursosList) { recurso, action ->
            when (action) {
                "aceptar" -> {
                    recurso.estatus = "Aceptado"
                    recyclerView.adapter?.notifyItemChanged(recursosList.indexOf(recurso))
                }
                "rechazar" -> {
                    recurso.estatus = "Rechazado"
                    recyclerView.adapter?.notifyItemChanged(recursosList.indexOf(recurso))
                }
            }
        }
        recyclerView.adapter = adapter

        // Simula datos iniciales
        recursosList.add(Recurso("Zona 1", "Recurso 1", "Pendiente"))
        recursosList.add(Recurso("Zona 2", "Recurso 2", "Pendiente"))
        recursosList.add(Recurso("Zona 3", "Recurso 3", "Pendiente"))

        // Botón flotante para agregar recursos
        val addResourceFab = findViewById<FloatingActionButton>(R.id.addResourceFab)
        addResourceFab.setOnClickListener {
            showAddResourceDialog(adapter)
        }
    }

    // Diálogo para agregar un nuevo recurso
    private fun showAddResourceDialog(adapter: RecursosAdapter) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Agregar Recurso")
        val view = layoutInflater.inflate(R.layout.dialog_add_resource, null)
        dialog.setView(view)

        val zonaInput = view.findViewById<EditText>(R.id.zonaInput)
        val recursoInput = view.findViewById<EditText>(R.id.recursoInput)

        dialog.setPositiveButton("Agregar") { _, _ ->
            val zona = zonaInput.text.toString()
            val recurso = recursoInput.text.toString()

            if (zona.isNotEmpty() && recurso.isNotEmpty()) {
                recursosList.add(Recurso(zona, recurso, "Pendiente"))
                adapter.notifyItemInserted(recursosList.size - 1)
                Toast.makeText(this, "Recurso agregado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.setNegativeButton("Cancelar", null)
        dialog.show()
    }
}
