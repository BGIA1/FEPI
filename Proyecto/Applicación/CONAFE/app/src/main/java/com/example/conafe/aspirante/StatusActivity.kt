package com.example.conafe.aspirante

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.conafe.R
import com.example.conafe.consulta
import com.example.conafe.consultaJSON
import org.json.JSONArray

class StatusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tabla_status)

        // Referencias a los TextViews en el diseño
        val tvEstatus = findViewById<TextView>(R.id.tvEstatus)
        val tvMunicipio = findViewById<TextView>(R.id.tvMunicipio)
        val tvEstado = findViewById<TextView>(R.id.tvEstado)

        // Recuperar datos de sesión
        val idUsuario = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE).getString("id_usuario", null)

        if(idUsuario!=null)
        {
            consultaJSON(
        "Aspirantes",
        """
                estatus,
                user_data:Usuarios!id_usuario 
                (
                    estado,
                    municipio
                )
                """,
                filtros = listOf("id_usuario" to idUsuario)) { resp,exito ->
                if(exito && resp!=null){
                    val jsonArray = JSONArray(resp)
                    println(jsonArray)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val userData = jsonObject.getJSONObject("user_data")
                        runOnUiThread {
                            tvEstatus.text=jsonObject.getString("estatus")
                            tvMunicipio.text=userData.getString("municipio")
                            tvEstado.text= userData.getString("estado")
                        }
                    }
                }
            }
        }
    }
}
