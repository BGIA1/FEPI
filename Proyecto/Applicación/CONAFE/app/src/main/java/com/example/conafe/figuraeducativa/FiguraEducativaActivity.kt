package com.example.conafe.figuraeducativa

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.conafe.R
import com.google.android.material.navigation.NavigationView
import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.conafe.aspirante.DepositosActivity
import com.example.conafe.ProfileActivity
import com.example.conafe.inicio.IntentUtils
import com.example.conafe.inicio.MainActivity


class FiguraEducativaActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_figuraeducativa)

        webView = findViewById(R.id.webViewFE)
        // Habilitar JavaScript (opcional)
        webView.settings.javaScriptEnabled = true
        // Configurar el WebView para que abra los enlaces dentro de la app
        webView.webViewClient = WebViewClient()
        // Cargar una URL
        webView.loadUrl("https://www.gob.mx/conafe")

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
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_alumnos -> {
                    // Acción para "Alumnos"
                    val intent = Intent(this, AlumnosActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_materias -> {
                    // Acción para "Materias"
                    val intent = Intent(this, CalificacionAlumnos::class.java)
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
                    val intent = Intent(this, DepositosActivity::class.java)
                    startActivity(intent)
                    true
                    // Acción para "Depósitos"
                }
                R.id.nav_reportes -> {
                    // Acción para "Reportes"
                    val intent = Intent(this, ReportesActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_logout_fe -> {
                    IntentUtils.cerrarSesion(this)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }
            drawerLayout.closeDrawers()
            true
        }
    }
}
