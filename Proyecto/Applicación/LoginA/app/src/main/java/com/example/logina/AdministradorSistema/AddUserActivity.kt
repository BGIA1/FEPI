package com.example.logina.AdministradorSistema

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.logina.DataBase.DatabaseConfig
import com.example.logina.DataBase.UsuariosTable
import com.example.logina.R

class AddUserActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        // Inicializar la base de datos
        dbHelper = DatabaseConfig(this)

        // Referencias a los elementos del formulario
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etApellidos = findViewById<EditText>(R.id.etApellidos)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etFechaNacimiento = findViewById<EditText>(R.id.etFechaNacimiento)
        val etCelular = findViewById<EditText>(R.id.etCelular)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val spTipoCuenta = findViewById<Spinner>(R.id.spTipoCuenta)
        val btnGuardarUsuario = findViewById<Button>(R.id.btnGuardarUsuario)

        // Configurar el Spinner con las opciones de tipo de cuenta
        val tiposCuenta = arrayOf(    "Coordinador",
            "Delegado",
            "Usuario",
            "Becario",
            "CoordinadorZona",
            "DelegadoEstatal",
            "Admin",
            "SupervisorModulo")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposCuenta)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTipoCuenta.adapter = adapter

        // Configurar acción del botón de guardar
        btnGuardarUsuario.setOnClickListener {
            val nombre = etNombre.text.toString().trim()
            val apellidos = etApellidos.text.toString().trim()
            val correo = etCorreo.text.toString().trim()
            val fechaNacimiento = etFechaNacimiento.text.toString().trim()
            val celular = etCelular.text.toString().trim()
            val contrasena = etContrasena.text.toString().trim()
            val tipoCuenta = spTipoCuenta.selectedItem.toString()

            if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || fechaNacimiento.isEmpty() ||
                celular.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Agregar usuario a la base de datos
            val db = dbHelper.writableDatabase
            val resultado = UsuariosTable.addUsuario(db, nombre, apellidos, correo, fechaNacimiento, celular, contrasena, tipoCuenta)

            if (resultado != -1L) {
                Toast.makeText(this, "Usuario guardado exitosamente", Toast.LENGTH_SHORT).show()
                limpiarFormulario(etNombre, etApellidos, etCorreo, etFechaNacimiento, etCelular, etContrasena, spTipoCuenta)
            } else {
                Toast.makeText(this, "Error al guardar usuario. Verifica el correo.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun limpiarFormulario(
        etNombre: EditText,
        etApellidos: EditText,
        etCorreo: EditText,
        etFechaNacimiento: EditText,
        etCelular: EditText,
        etContrasena: EditText,
        spTipoCuenta: Spinner
    ) {
        etNombre.text.clear()
        etApellidos.text.clear()
        etCorreo.text.clear()
        etFechaNacimiento.text.clear()
        etCelular.text.clear()
        etContrasena.text.clear()
        spTipoCuenta.setSelection(0)
    }
}
