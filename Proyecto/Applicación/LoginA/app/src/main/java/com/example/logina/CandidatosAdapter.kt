package com.example.logina

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Candidato(
    val nombre: String,
    val email: String,
    val telefono: String,
    var status: String = "none" // Valores: "none", "accepted", "rejected"
)

class CandidatosAdapter(
    private val candidatos: List<Candidato>,
    private val onAction: (Candidato, String, String?) -> Unit
) : RecyclerView.Adapter<CandidatosAdapter.CandidatoViewHolder>() {

    class CandidatoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.candidatoNombre)
        val email: TextView = view.findViewById(R.id.candidatoEmail)
        val telefono: TextView = view.findViewById(R.id.candidatoTelefono)
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
        holder.nombre.text = candidato.nombre
        holder.email.text = candidato.email
        holder.telefono.text = candidato.telefono

        // Cambiar fondo según el estado
        when (candidato.status) {
            "accepted" -> holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#DFF2BF")) // Fondo verde
            "rejected" -> holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#FFBABA")) // Fondo rojo
            else -> holder.itemView.setBackgroundColor(android.graphics.Color.WHITE) // Fondo predeterminado
        }

        // Visibilidad del Spinner
        holder.rejectionReasonSpinner.visibility =
            if (candidato.status == "rejected") View.VISIBLE else View.GONE

        // Acción para visualizar documentos
        holder.viewDocumentsButton.setOnClickListener {
            onAction(candidato, "view", null)
        }

        // Acción para aceptar
        holder.acceptButton.setOnClickListener {
            candidato.status = "accepted"
            notifyItemChanged(position)
            onAction(candidato, "accept", null)
        }

        // Acción para rechazar
        holder.rejectButton.setOnClickListener {
            candidato.status = "rejected"
            notifyItemChanged(position)
        }

        // Configurar el Spinner para rechazo
        holder.rejectionReasonSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val reason = parent.getItemAtPosition(position).toString()
                onAction(candidato, "reject", reason)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // No se seleccionó ningún motivo
            }
        }
    }

    override fun getItemCount(): Int = candidatos.size
}
