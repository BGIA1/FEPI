package com.example.logina.AdministradorSistema

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.logina.R
import com.example.logina.DataBase.Usuario

class UserAdapter(
    private val users: MutableList<Usuario>,
    private val onDelete: (Usuario) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userNameTextView: TextView = view.findViewById(R.id.userNameTextView)
        val userEmailTextView: TextView = view.findViewById(R.id.userEmailTextView)
        val deleteUserButton: Button = view.findViewById(R.id.deleteUserButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.userNameTextView.text = "${user.nombre} ${user.apellidos}"
        holder.userEmailTextView.text = user.correo

        holder.deleteUserButton.setOnClickListener {
            onDelete(user)
        }
    }

    override fun getItemCount(): Int = users.size
}
