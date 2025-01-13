package com.example.conafe.supervisor

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.R
import com.example.conafe.figuraeducativa.BecasActivity

class InicioSupervisorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_supervisor)

        // Configuración del Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    // Infla el menú de opciones
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_supervisor, menu)
        return true
    }

    // Manejo de clics en el menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_perfil -> {
                Toast.makeText(this, "Perfil seleccionado", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_evaluar_docentes -> {
                val intent = Intent(this, EvaluarDocenteActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menu_recursos -> {
                val intent = Intent(this, RecursosActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
