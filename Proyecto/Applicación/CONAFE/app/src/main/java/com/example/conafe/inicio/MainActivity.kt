package com.example.conafe.inicio

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.conafe.R
import com.example.conafe.aspirante.StatusActivity
import com.example.conafe.initSupaClient

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuración del fondo animado
        val constraintLayout = findViewById<ConstraintLayout>(R.id.mainLayout)
        val animationDrawable = constraintLayout.background as AnimationDrawable
        //animationDrawable.setEnterFadeDuration(500)
        //animationDrawable.setExitFadeDuration(500)
        //animationDrawable.start()

        initSupaClient()

        // Obtener las SharedPreferences
        val sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        // Recuperar datos de sesión
        val idUsuario = sharedPreferences.getString("id_usuario", null)
        val tipoUsuario = sharedPreferences.getString("tipoUsuario", null)

        // Retraso para pasar a la pantalla de inicio de sesión
        Handler(Looper.getMainLooper()).postDelayed({
            if(tipoUsuario!=null && idUsuario!=null)
            {
                IntentUtils.cargarMenuUsuario(idUsuario,tipoUsuario,this)
                //val intent = Intent(this, StatusActivity::class.java)
                //startActivity(intent)
                //finish() // Finaliza la actividad actual para que no regrese al presionar "atrás"
            }
            else
            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // Finaliza la actividad actual para que no regrese al presionar "atrás"
            }
        }, 500) // Cambia a 3000 ms (3 segundos) o al tiempo que prefieras
    }
}