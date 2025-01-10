package com.example.logina

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.widget.Button
import android.widget.Toast

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sessionManager = SessionManager(this)

        // Verificar si hay sesión activa
        if (!sessionManager.isLoggedIn()) {
            navigateToLogin()
        }

        // Configuración de DrawerLayout y Toolbar
        drawerLayout = findViewById(R.id.drawerLayout)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configuración de NavigationView
        val navigationView = findViewById<NavigationView>(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)

        // Configuración del botón Convocatoria Abierta (si lo usas en este caso)
        val convocatoriaButton = findViewById<Button>(R.id.convocatoriaAbiertaButton)
        convocatoriaButton.setOnClickListener {
            Toast.makeText(this, "Convocatoria Abierta clickeado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                Toast.makeText(this, "Perfil seleccionado", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                sessionManager.clearSession()
                navigateToLogin()
            }
            else -> {
                Toast.makeText(this, "Opción no reconocida", Toast.LENGTH_SHORT).show()
            }
        }
        drawerLayout.closeDrawers()
        return true
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
