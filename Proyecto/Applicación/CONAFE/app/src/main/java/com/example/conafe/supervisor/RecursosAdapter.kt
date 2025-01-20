package com.example.conafe.supervisor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.conafe.R


class RecursosAdapter(private val alumnos: List<String>) :
    RecyclerView.Adapter<RecursosAdapter.RecursoViewHolder>() {

    // Clase interna para el ViewHolder
    class RecursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tvAlumno)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecursoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alumno, parent, false)
        return RecursoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecursoViewHolder, position: Int) {
        holder.textView.text = alumnos[position]
    }

    override fun getItemCount(): Int = alumnos.size
}
