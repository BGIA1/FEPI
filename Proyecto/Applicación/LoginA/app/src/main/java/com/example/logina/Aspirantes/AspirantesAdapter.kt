package com.example.logina.Aspirantes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.logina.R

class AspirantesAdapter(
    private val aspirantes: List<Aspirante>,
    private val onAction: (Aspirante, String) -> Unit
) : RecyclerView.Adapter<AspirantesAdapter.AspiranteViewHolder>() {

    class AspiranteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idTextView: TextView = view.findViewById(R.id.idTextView)
        val nombreTextView: TextView = view.findViewById(R.id.nombreTextView)
        val correoTextView: TextView = view.findViewById(R.id.correoTextView)
        val aceptarButton: Button = view.findViewById(R.id.aceptarButton)
        val rechazarButton: Button = view.findViewById(R.id.rechazarButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AspiranteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_aspirante, parent, false)
        return AspiranteViewHolder(view)
    }

    override fun onBindViewHolder(holder: AspiranteViewHolder, position: Int) {
        val aspirante = aspirantes[position]
        holder.idTextView.text = "ID: ${aspirante.id}"
        holder.nombreTextView.text = "Nombre: ${aspirante.nombre}"
        holder.correoTextView.text = "Correo: ${aspirante.correo}"

        holder.aceptarButton.setOnClickListener {
            onAction(aspirante, "accept")
        }

        holder.rechazarButton.setOnClickListener {
            onAction(aspirante, "reject")
        }
    }

    override fun getItemCount(): Int = aspirantes.size
}
