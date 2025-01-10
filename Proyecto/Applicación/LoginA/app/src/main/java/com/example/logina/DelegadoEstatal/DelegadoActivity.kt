package com.example.logina.DelegadoEstatal

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.logina.Global.PerfilActivity
import com.example.logina.R
import com.google.android.material.navigation.NavigationView

class DelegadoActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delegado)

        // Configurar Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Delegado Estatal"

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
                R.id.nav_profile -> {
                    // Redirigir a PerfilActivity
                    val intent = Intent(this, PerfilActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_convocatoria -> {
                    // Redirigir a ConvocatoriaActivity
                    val intent = Intent(this, ConvocatoriaActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_recursos -> {
                    // Redirigir a RecursosActivity
                    val intent = Intent(this, RecursosActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_centros_educativos -> {
                    // Redirigir a CentrosEducativosActivity
                    val intent = Intent(this, CentrosEducativosActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_delegaciones -> {
                    // Redirigir a DelegacionesActivity
                    val intent = Intent(this, DelegacionesActivity::class.java)
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
