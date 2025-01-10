package com.example.logina.AdministradorSistema

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.logina.R // Importar la clase R para acceder a los recursos
import com.example.logina.AdministradorSistema.DeleteUserActivity // Ruta completa para DeleteUserActivity
import com.google.android.material.navigation.NavigationView

class AdminActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin) // Referencia al diseño de actividad

        // Configurar Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.adminToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Administrador del Sistema"

        // Configurar DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.adminDrawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.adminNavigationView)

        // Configurar ActionBarDrawerToggle
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer, // Referencia a cadena
            R.string.close_drawer // Referencia a cadena
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configurar acciones del menú
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_add_center -> {
                    Toast.makeText(this, "Seleccionado: Dar de alta centro", Toast.LENGTH_SHORT).show()
                }
                // Redirigir a la pantalla AddUserActivity
                R.id.nav_add_user -> {
                    val intent = Intent(this, AddUserActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_manage_permissions -> {
                    // Redirigir a la pantalla ModifyUserActivity
                    val intent = Intent(this, ModifyUserActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_delete_user -> {
                    // Redirigir a la pantalla DeleteUserActivity
                    val intent = Intent(this, DeleteUserActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, "Opción no reconocida", Toast.LENGTH_SHORT).show()
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
