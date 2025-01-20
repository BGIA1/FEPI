package com.example.conafe.inicio

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.conafe.aspirante.AspirantesActivity
import com.example.conafe.coordinador.CoordinadorZonaActivity
import com.example.conafe.figuraeducativa.FiguraEducativaActivity
import com.example.conafe.supervisor.InicioSupervisorActivity

object IntentUtils
{
    fun cargarMenuUsuario(idUsuario:String,tipoUsuario: String,context: Context) {
        var intent=Intent(context, AspirantesActivity::class.java)
        when (tipoUsuario) {
            "Aspirante" -> {
                // Redirigir a PerfilActivity
                intent = Intent(context, AspirantesActivity::class.java)
            }
            "Coordinador" -> {
                // Redirigir a PerfilActivity
                intent = Intent(context, CoordinadorZonaActivity::class.java)
            }
            "Supervisor" -> {
                // Redirigir a PerfilActivity
                intent = Intent(context, InicioSupervisorActivity::class.java)
            }
            "FE" -> {
                // Redirigir a PerfilActivity
                intent = Intent(context, FiguraEducativaActivity::class.java)
            }
            else -> {
                // Opción no reconocida
                Toast.makeText(context, "Opción no reconocida", Toast.LENGTH_SHORT).show()
            }
        }
        intent.putExtra("id_Usuario", idUsuario)
        context.startActivity(intent)
    }

    fun cerrarSesion(context: Context)
    {
        // Obtener el editor de SharedPreferences
        val sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Guardar datos de sesión
        editor.putString("id_usuario", null)
        editor.putString("tipoUsuario", null)
        editor.apply() // Aplicar cambios
            Toast.makeText(context, "Se cerró la sesión con éxito", Toast.LENGTH_SHORT).show()
    }
}
