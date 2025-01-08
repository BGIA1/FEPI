package com.example.logina

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Configura la Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        // Configura el DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawerLayout)
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        // Configura el ActionBarDrawerToggle
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Configura las opciones del menú lateral
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_personal_info -> {
                    val intent = Intent(this, PersonalInfoActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_call -> {
                    val intent = Intent(this, CallActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_skills -> {
                    val intent = Intent(this, SkillsActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    Toast.makeText(this, "Opción no reconocida", Toast.LENGTH_SHORT).show()
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        // Configura el FloatingActionButton
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
        }

        // Configura el botón "Convocatoria Abierta"
        val convocatoriaAbiertaButton = findViewById<Button>(R.id.convocatoriaAbiertaButton)

        // Recuperar datos de SharedPreferences
        val sharedPreferences = getSharedPreferences("ConvocatoriaPrefs", MODE_PRIVATE)
        val convocatoriaActiva = sharedPreferences.getString("convocatoriaActiva", null)

        // Mostrar el botón solo si la convocatoria activa es "CONAFE 2025"
        if (convocatoriaActiva == "CONAFE 2025") {
            convocatoriaAbiertaButton.visibility = Button.VISIBLE
            convocatoriaAbiertaButton.setOnClickListener {
                val intent = Intent(this, RegistroConvocatoriaActivity::class.java)
                intent.putExtra("convocatoriaNombre", convocatoriaActiva)
                startActivity(intent)
            }
        } else {
            convocatoriaAbiertaButton.visibility = Button.GONE
        }
    }

    // Manejar clics en el botón de hamburguesa
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
