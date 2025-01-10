package com.example.logina.AdministradorSistema

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.logina.DataBase.Usuario
import com.example.logina.DataBase.UsuariosTable
import com.example.logina.R
import android.widget.ArrayAdapter


class ModifyUserAdapter(
    private val usuarios: MutableList<Usuario>,
    private val onUpdate: (Usuario, String) -> Unit
) : RecyclerView.Adapter<ModifyUserAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvCorreo: TextView = view.findViewById(R.id.tvCorreo)
        val spTipoCuenta: Spinner = view.findViewById(R.id.spTipoCuenta)
        val btnGuardar: Button = view.findViewById(R.id.btnGuardar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_modify_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.tvNombre.text = "${usuario.nombre} ${usuario.apellidos}"
        holder.tvCorreo.text = usuario.correo

        // Configurar Spinner
        val tiposCuenta = arrayOf(    "Coordinador",
            "Delegado",
            "Usuario",
            "Becario",
            "CoordinadorZona",
            "DelegadoEstatal",
            "Admin",
            "SupervisorModulo")
        val adapter = ArrayAdapter(holder.itemView.context, android.R.layout.simple_spinner_item, tiposCuenta)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.spTipoCuenta.adapter = adapter

        // Seleccionar el tipo de cuenta actual
        val currentIndex = tiposCuenta.indexOf(usuario.tipoCuenta)
        if (currentIndex != -1) {
            holder.spTipoCuenta.setSelection(currentIndex)
        }

        // Guardar cambios
        holder.btnGuardar.setOnClickListener {
            val nuevoTipoCuenta = holder.spTipoCuenta.selectedItem.toString()
            onUpdate(usuario, nuevoTipoCuenta)
        }
    }

    override fun getItemCount(): Int = usuarios.size
}
