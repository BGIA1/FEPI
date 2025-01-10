package com.example.logina.AdministradorSistema

import android.content.ContentValues // Importar ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logina.DataBase.DatabaseConfig
import com.example.logina.DataBase.Usuario
import com.example.logina.DataBase.UsuariosTable
import com.example.logina.R

class ModifyUserActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseConfig
    private lateinit var usuariosRecyclerView: RecyclerView
    private val userList = mutableListOf<Usuario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_user)

        dbHelper = DatabaseConfig(this)
        usuariosRecyclerView = findViewById(R.id.rvUsuarios)

        // Cargar usuarios
        loadUsuarios()

        // Configurar RecyclerView
        usuariosRecyclerView.layoutManager = LinearLayoutManager(this)
        usuariosRecyclerView.adapter = ModifyUserAdapter(userList) { usuario, nuevoTipoCuenta ->
            modificarTipoCuenta(usuario, nuevoTipoCuenta)
        }
    }

    private fun loadUsuarios() {
        userList.clear()
        val db = dbHelper.readableDatabase
        userList.addAll(UsuariosTable.getAllUsuarios(db))
        usuariosRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun modificarTipoCuenta(usuario: Usuario, nuevoTipoCuenta: String) {
        val db = dbHelper.writableDatabase
        val valores = ContentValues().apply {
            put(UsuariosTable.COLUMN_TIPO_CUENTA, nuevoTipoCuenta)
        }
        val filasActualizadas = db.update(
            UsuariosTable.TABLE_NAME,
            valores,
            "${UsuariosTable.COLUMN_ID} = ?",
            arrayOf(usuario.id.toString())
        )

        if (filasActualizadas > 0) {
            Toast.makeText(this, "Tipo de cuenta actualizado para ${usuario.nombre}", Toast.LENGTH_SHORT).show()
            loadUsuarios()
        } else {
            Toast.makeText(this, "Error al actualizar tipo de cuenta", Toast.LENGTH_SHORT).show()
        }
    }
}
