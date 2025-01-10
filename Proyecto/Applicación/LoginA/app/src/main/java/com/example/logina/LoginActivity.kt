package com.example.logina

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logina.Aspirantes.PantallaInicioBecario
import com.example.logina.CordinadorZona.CoordinadorActivity
import com.example.logina.DelegadoEstatal.DelegadoActivity
import com.example.logina.SupervisorModulo.SupervisorActivity
import com.example.logina.Usuario.HomeActivity
import com.example.logina.AdministradorSistema.AdminActivity
import com.example.logina.DataBase.DatabaseConfig
import com.example.logina.DataBase.UsuariosTable

class LoginActivity : AppCompatActivity() {

    // Usuarios predeterminados
    private val defaultUsers = listOf(
        Triple("usuario@gmail.com", "123456", "Usuario"),
        Triple("bot@becario.com", "123456", "Becario"),
        Triple("bot@coordinadorzona.com", "123456", "CoordinadorZona"),
        Triple("bot@delegadoestatal.com", "123456", "DelegadoEstatal"),
        Triple("bot@admin", "123456", "Admin"),
        Triple("bot@supervisormodulo.com", "123456", "SupervisorModulo")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sessionManager = SessionManager(this)
        val dbHelper = DatabaseConfig(this)

        // Inicializar base de datos y agregar usuarios predeterminados si no existen
        addDefaultUsersToDatabase(dbHelper)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = dbHelper.readableDatabase
            val cursor = db.query(
                UsuariosTable.TABLE_NAME,
                arrayOf(UsuariosTable.COLUMN_ID, UsuariosTable.COLUMN_NOMBRE, UsuariosTable.COLUMN_CONTRASENA, UsuariosTable.COLUMN_TIPO_CUENTA),
                "${UsuariosTable.COLUMN_CORREO} = ?",
                arrayOf(email),
                null,
                null,
                null
            )

            if (cursor.moveToFirst()) {
                val userId = cursor.getInt(cursor.getColumnIndexOrThrow(UsuariosTable.COLUMN_ID))
                val userName = cursor.getString(cursor.getColumnIndexOrThrow(UsuariosTable.COLUMN_NOMBRE))
                val storedPassword = cursor.getString(cursor.getColumnIndexOrThrow(UsuariosTable.COLUMN_CONTRASENA))
                val userType = cursor.getString(cursor.getColumnIndexOrThrow(UsuariosTable.COLUMN_TIPO_CUENTA))

                if (password == storedPassword) {
                    sessionManager.saveUserSession(userId, userName, email, userType)

                    when (userType) {
                        "Becario" -> navigateTo(PantallaInicioBecario::class.java, "Becario")
                        "CoordinadorZona" -> navigateTo(CoordinadorActivity::class.java, "Coordinador de Zona")
                        "DelegadoEstatal" -> navigateTo(DelegadoActivity::class.java, "Delegado Estatal")
                        "Admin" -> navigateTo(AdminActivity::class.java, "Administrador")
                        "SupervisorModulo" -> navigateTo(SupervisorActivity::class.java, "Supervisor de Módulo")
                        else -> navigateTo(HomeActivity::class.java, "Usuario")
                    }
                } else {
                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Correo no registrado", Toast.LENGTH_SHORT).show()
            }

            cursor.close()
        }
    }

    private fun navigateTo(activityClass: Class<*>, roleName: String) {
        Toast.makeText(this, "Inicio de sesión como $roleName", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, activityClass)
        startActivity(intent)
        finish()
    }

    private fun addDefaultUsersToDatabase(dbHelper: DatabaseConfig) {
        val db = dbHelper.writableDatabase
        defaultUsers.forEach { (correo, contrasena, tipoCuenta) ->
            val cursor = db.query(
                UsuariosTable.TABLE_NAME,
                arrayOf(UsuariosTable.COLUMN_CORREO),
                "${UsuariosTable.COLUMN_CORREO} = ?",
                arrayOf(correo),
                null,
                null,
                null
            )
            if (!cursor.moveToFirst()) {
                val values = ContentValues().apply {
                    put(UsuariosTable.COLUMN_NOMBRE, "Default")
                    put(UsuariosTable.COLUMN_APELLIDOS, "User")
                    put(UsuariosTable.COLUMN_CORREO, correo)
                    put(UsuariosTable.COLUMN_FECHA_NACIMIENTO, "01/01/2000")
                    put(UsuariosTable.COLUMN_CELULAR, "0000000000")
                    put(UsuariosTable.COLUMN_CONTRASENA, contrasena)
                    put(UsuariosTable.COLUMN_TIPO_CUENTA, tipoCuenta)
                }
                db.insert(UsuariosTable.TABLE_NAME, null, values)
            }
            cursor.close()
        }
    }
}
