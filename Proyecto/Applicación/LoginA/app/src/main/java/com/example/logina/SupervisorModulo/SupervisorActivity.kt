package com.example.logina.SupervisorModulo

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.logina.R
import com.google.android.material.navigation.NavigationView

class SupervisorActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supervisor)

        // Configurar Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Supervisor de Módulo"

        // Configurar DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        // Configurar ActionBarDrawerToggle
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configurar acciones del menú
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_capacitacion -> {
                    // Redirigir a CapacitacionActivity
                    val intent = Intent(this, CapacitacionActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_supervision -> {
                    // Redirigir a SupervisionActivity
                    val intent = Intent(this, SupervisionActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_reportes -> {
                    // Redirigir a ReportesActivity
                    val intent = Intent(this, ReportesActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    // Opción no reconocida
                    Toast.makeText(this, "Opción no reconocida", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawers() // Cierra el menú después de seleccionar
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
