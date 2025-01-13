package com.example.conafe.figuraeducativa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.conafe.R

class BecasAdapter(private val becas: List<Beca>, private val onFormClick: (String) -> Unit) :
    RecyclerView.Adapter<BecasAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.tvNombreBeca)
        val estatus: TextView = view.findViewById(R.id.tvEstatus)
        val botonFormulario: Button = view.findViewById(R.id.btnFormulario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_beca, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beca = becas[position]
        holder.nombre.text = beca.nombre
        holder.estatus.text = beca.estatus
        holder.botonFormulario.setOnClickListener {
            onFormClick(beca.urlFormulario) // Abrir URL al hacer clic
        }
    }

    override fun getItemCount(): Int = becas.size
}
