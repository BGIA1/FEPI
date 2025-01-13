package com.example.conafe.figuraeducativa

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.conafe.R

class BecasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_becas)

        val becas = listOf(
            Beca("Beca Excelencia", "Aceptada", "https://ejemplo.com/formulario1"),
            Beca("Beca Apoyo Económico", "Rechazada", "https://ejemplo.com/formulario2"),
            Beca("Beca Transporte", "Aceptada", "https://ejemplo.com/formulario3")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rvBecas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BecasAdapter(becas) { url ->
            abrirFormularioWeb(url)
        }
    }

    private fun abrirFormularioWeb(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
