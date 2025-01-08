package com.example.logina

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ConvocatoriaAdapter(
    private val convocatorias: List<Convocatoria>,
    private val onDelete: (Convocatoria) -> Unit
) : RecyclerView.Adapter<ConvocatoriaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_convocatoria, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val convocatoria = convocatorias[position]
        holder.nameTextView.text = convocatoria.name
        holder.dateTextView.text = "${convocatoria.startDate} - ${convocatoria.endDate}"
        holder.deleteButton.setOnClickListener { onDelete(convocatoria) }
    }

    override fun getItemCount(): Int = convocatorias.size
}
