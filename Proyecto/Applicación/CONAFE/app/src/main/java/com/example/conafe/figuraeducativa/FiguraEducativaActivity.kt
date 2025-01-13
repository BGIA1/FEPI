package com.example.conafe.figuraeducativa

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.conafe.R
import com.google.android.material.navigation.NavigationView
import android.content.Intent


class FiguraEducativaActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_figuraeducativa)

        // Configurar Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(resources.getColor(android.R.color.white, theme))
        // Configurar DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)

        drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        // Configurar acciones del menú
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_perfil -> {
                    // Acción para "Perfil"
                }
                R.id.nav_alumnos -> {
                    // Acción para "Alumnos"
                    val intent = Intent(this, AlumnosActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_materias -> {
                    // Acción para "Materias"
                    val intent = Intent(this, MateriasActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_becas -> {
                    // Acción para "Becas"
                    val intent = Intent(this, BecasActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_depositos -> {
                    // Acción para "Depósitos"
                }
                R.id.nav_reportes -> {
                    // Acción para "Reportes"
                    val intent = Intent(this, ReportesActivity::class.java)
                    startActivity(intent)
                    true
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }
}
