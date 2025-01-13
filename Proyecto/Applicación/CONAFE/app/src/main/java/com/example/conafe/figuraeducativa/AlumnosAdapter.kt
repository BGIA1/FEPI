package com.example.conafe.figuraeducativa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.conafe.R


class AlumnosAdapter(private val alumnos: List<String>) :
    RecyclerView.Adapter<AlumnosAdapter.AlumnoViewHolder>() {

    // Clase interna para el ViewHolder
    class AlumnoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.tvAlumno)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alumno, parent, false)
        return AlumnoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        holder.textView.text = alumnos[position]
    }

    override fun getItemCount(): Int = alumnos.size
}
