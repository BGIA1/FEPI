package com.example.conafe.delegado

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.conafe.R
import com.example.conafe.aspirante.ProfileActivity
import com.google.android.material.navigation.NavigationView

class DelegadoEstatalActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_delegado_estatal_activity)

        // Configurar Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.delegadoToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Menú del Delegado Estatal"

        // Configurar DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.delegadoDrawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.delegadoNavigationView)

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
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_send_resources -> {
                    val intent = Intent(this, EnviarRecursosActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    // Acción por defecto si no se reconoce la opción
                }
            }
            drawerLayout.closeDrawers()
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
