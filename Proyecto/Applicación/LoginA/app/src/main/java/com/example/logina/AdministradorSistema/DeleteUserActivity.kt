package com.example.logina.AdministradorSistema

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logina.DataBase.DatabaseConfig
import com.example.logina.DataBase.UsuariosTable
import com.example.logina.R
import com.example.logina.DataBase.Usuario

class DeleteUserActivity : AppCompatActivity() {

    private lateinit var database: DatabaseConfig
    private lateinit var usersRecyclerView: RecyclerView
    private val userList = mutableListOf<Usuario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_user)

        // Inicializar la base de datos
        database = DatabaseConfig(this)
        usersRecyclerView = findViewById(R.id.usersRecyclerView)

        // Cargar usuarios desde la base de datos
        loadUsers()

        // Configurar RecyclerView
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecyclerView.adapter = UserAdapter(userList) { user ->
            // Eliminar usuario al hacer clic en el botón
            deleteUser(user)
        }
    }

    private fun loadUsers() {
        userList.clear()
        val db = database.readableDatabase
        userList.addAll(UsuariosTable.getAllUsuarios(db)) // Obtener usuarios desde la tabla
        usersRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun deleteUser(user: Usuario) {
        val db = database.writableDatabase
        val rowsDeleted = db.delete(
            UsuariosTable.TABLE_NAME,
            "${UsuariosTable.COLUMN_CORREO} = ?",
            arrayOf(user.correo)
        )
        if (rowsDeleted > 0) {
            Toast.makeText(this, "Usuario eliminado: ${user.nombre} ${user.apellidos}", Toast.LENGTH_SHORT).show()
            loadUsers() // Recargar la lista después de eliminar
        } else {
            Toast.makeText(this, "Error al eliminar usuario", Toast.LENGTH_SHORT).show()
        }
    }
}
