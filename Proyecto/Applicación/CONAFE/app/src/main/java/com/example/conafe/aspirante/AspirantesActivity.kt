package com.example.conafe.aspirante

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.conafe.ProfileActivity
import com.example.conafe.R
import com.example.conafe.inicio.IntentUtils
import com.example.conafe.inicio.MainActivity

import com.google.android.material.navigation.NavigationView

class AspirantesActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_alumno_activity)

        webView = findViewById(R.id.webView)
        // Habilitar JavaScript (opcional)
        webView.settings.javaScriptEnabled = true
        // Configurar el WebView para que abra los enlaces dentro de la app
        webView.webViewClient = WebViewClient()
        // Cargar una URL
        webView.loadUrl("https://www.gob.mx/conafe")


        //ID del usuario con la sesion actual
        val idUsuario = intent.getStringExtra("id_usuario")

        // Configurar Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Menú del Aspirante"

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
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_status -> {
                    val intent = Intent(this, StatusActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_subjects -> {
                    val intent = Intent(this, MateriasAspirante::class.java)
                    startActivity(intent)
                }
                R.id.nav_deposits -> {
                    val intent = Intent(this, DepositosActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_application -> {
                    val intent = Intent(this, PostulacionActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_calendar -> {
                    val intent = Intent(this, CalendarioActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_logout -> {
                    IntentUtils.cerrarSesion(this)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
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

    // Manejar el botón de retroceso para la navegación web
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
