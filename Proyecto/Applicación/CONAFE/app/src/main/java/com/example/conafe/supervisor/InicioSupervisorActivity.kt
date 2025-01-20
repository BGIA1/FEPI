package com.example.conafe.supervisor

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.conafe.ProfileActivity
import com.example.conafe.R
import com.example.conafe.figuraeducativa.BecasActivity
import com.example.conafe.inicio.IntentUtils
import com.example.conafe.inicio.MainActivity

class InicioSupervisorActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_supervisor)

        webView = findViewById(R.id.webViewSM)
        // Habilitar JavaScript (opcional)
        webView.settings.javaScriptEnabled = true
        // Configurar el WebView para que abra los enlaces dentro de la app
        webView.webViewClient = WebViewClient()
        // Cargar una URL
        webView.loadUrl("https://www.gob.mx/conafe")

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
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
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
            R.id.nav_logout_sm -> {
                IntentUtils.cerrarSesion(this)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
