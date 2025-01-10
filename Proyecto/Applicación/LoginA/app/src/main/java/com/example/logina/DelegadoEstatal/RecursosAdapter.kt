package com.example.logina.DelegadoEstatal

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.logina.R
import com.example.logina.DelegadoEstatal.Recurso

class RecursosAdapter(
    private val recursos: List<Recurso>,
    private val onAction: (Recurso, String) -> Unit
) : RecyclerView.Adapter<RecursosAdapter.RecursoViewHolder>() {

    class RecursoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val zona: TextView = view.findViewById(R.id.recursoZona)
        val recurso: TextView = view.findViewById(R.id.recursoNombre)
        val estatus: TextView = view.findViewById(R.id.recursoEstatus)
        val aceptarButton: Button = view.findViewById(R.id.aceptarButton)
        val rechazarButton: Button = view.findViewById(R.id.rechazarButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecursoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recurso, parent, false)
        return RecursoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecursoViewHolder, position: Int) {
        val recurso = recursos[position]

        holder.zona.text = recurso.zona
        holder.recurso.text = recurso.recurso
        holder.estatus.text = recurso.estatus

        // Configurar botones
        holder.aceptarButton.setOnClickListener {
            recurso.estatus = "Aceptado"
            holder.estatus.text = recurso.estatus
            holder.estatus.setTextColor(Color.GREEN)
            onAction(recurso, "aceptar")
        }

        holder.rechazarButton.setOnClickListener {
            recurso.estatus = "Rechazado"
            holder.estatus.text = recurso.estatus
            holder.estatus.setTextColor(Color.RED)
            onAction(recurso, "rechazar")
        }

        // Cambiar el color del texto del estatus
        holder.estatus.setTextColor(
            when (recurso.estatus) {
                "Aceptado" -> Color.GREEN
                "Rechazado" -> Color.RED
                else -> Color.BLACK
            }
        )
    }

    override fun getItemCount(): Int = recursos.size
}
