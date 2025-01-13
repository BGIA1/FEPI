package com.example.conafe.figuraeducativa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.conafe.R
import android.widget.EditText


// Usa la clase Materia definida en Materia.kt
import com.example.conafe.figuraeducativa.Materia

class MateriasAdapter(
    private val materias: List<Materia>,
    private val onSaveCalificacion: (Materia, String) -> Unit
) : RecyclerView.Adapter<MateriasAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.tvNombreMateria)
        val calificacion: EditText = view.findViewById(R.id.etCalificacion)
        val guardar: Button = view.findViewById(R.id.btnGuardar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_materia, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val materia = materias[position]
        holder.nombre.text = materia.nombre

        // Manejar clic en el botón Guardar
        holder.guardar.setOnClickListener {
            val calificacion = holder.calificacion.text.toString()
            onSaveCalificacion(materia, calificacion)
        }
    }

    override fun getItemCount(): Int = materias.size
}
