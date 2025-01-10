package com.example.logina.CordinadorZona

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.logina.R

// Definición de la clase Candidato
data class Candidato(
    val nombre: String,
    val correo: String,
    val status: String
)

class CandidatosAdapter(
    private val candidatos: List<Candidato>, // Lista de candidatos
    private val onAction: (Candidato, String, String?) -> Unit // Callback para acciones
) : RecyclerView.Adapter<CandidatosAdapter.CandidatoViewHolder>() {

    class CandidatoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.candidatoNombre)
        val email: TextView = view.findViewById(R.id.candidatoEmail)
        val viewDocumentsButton: Button = view.findViewById(R.id.viewDocumentsButton)
        val acceptButton: Button = view.findViewById(R.id.acceptButton)
        val rejectButton: Button = view.findViewById(R.id.rejectButton)
        val rejectionReasonSpinner: Spinner = view.findViewById(R.id.rejectionReasonSpinner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidatoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_candidato, parent, false)
        return CandidatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CandidatoViewHolder, position: Int) {
        val candidato = candidatos[position]
        holder.nombre.text = "Nombre: ${candidato.nombre}" // Mostrar nombre del candidato
        holder.email.text = "Correo: ${candidato.correo}"   // Mostrar correo del candidato

        // Fondo según el estado
        when (candidato.status) {
            "aceptado" -> holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#DFF2BF")) // Verde
            "rechazado" -> holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#FFBABA")) // Rojo
            else -> holder.itemView.setBackgroundColor(android.graphics.Color.WHITE) // Predeterminado
        }

        // Visibilidad del Spinner según estado
        holder.rejectionReasonSpinner.visibility =
            if (candidato.status == "rechazado") View.VISIBLE else View.GONE

        // Acción: Ver documentos
        holder.viewDocumentsButton.setOnClickListener {
            onAction(candidato, "view", null)
        }

        // Acción: Aceptar
        holder.acceptButton.setOnClickListener {
            onAction(candidato, "accept", null)
        }

        // Acción: Rechazar
        holder.rejectButton.setOnClickListener {
            onAction(candidato, "reject", null)
        }

        // Configuración del Spinner para razones de rechazo
        holder.rejectionReasonSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val reason = parent.getItemAtPosition(position).toString()
                if (reason.isNotEmpty()) {
                    onAction(candidato, "reject", reason)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No hacer nada si no se selecciona un motivo
            }
        }
    }

    override fun getItemCount(): Int = candidatos.size
}
