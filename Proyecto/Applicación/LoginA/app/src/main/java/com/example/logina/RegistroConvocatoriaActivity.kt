package com.example.logina

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logina.DataBase.DatabaseConfig

class RegistroConvocatoriaActivity : AppCompatActivity() {

    private val PICK_PDF_REQUEST = 1
    private var selectedPdfUri: Uri? = null
    private lateinit var dbConfig: DatabaseConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_convocatoria)

        val convocatoriaNombreTextView = findViewById<TextView>(R.id.convocatoriaNombreTextView)
        val registroNombreEditText = findViewById<EditText>(R.id.registroNombreEditText)
        val registroEmailEditText = findViewById<EditText>(R.id.registroEmailEditText)
        val registroTelefonoEditText = findViewById<EditText>(R.id.registroTelefonoEditText)
        val subirDocumentacionButton = findViewById<Button>(R.id.subirDocumentacionButton)
        val actualizarConvocatoriaButton = findViewById<Button>(R.id.subirDocumentacionButton)

        // Inicializar la configuración de la base de datos
        dbConfig = DatabaseConfig(this)

        // Obtener el nombre de la convocatoria activa
        val convocatoriaNombre = intent.getStringExtra("convocatoriaNombre")
        convocatoriaNombreTextView.text = "Convocatoria Activa: $convocatoriaNombre"

        // Subir Documentación
        subirDocumentacionButton.setOnClickListener {
            openFilePicker()
        }

        // Actualizar el valor de COLUMN_CONVOCATORIA para todos los registros
        actualizarConvocatoriaButton.setOnClickListener {
            actualizarConvocatoria("nueva convocatoria")
        }
    }

    private fun actualizarConvocatoria(nuevaConvocatoria: String) {
        Toast.makeText(this, "Funcionalidad eliminada", Toast.LENGTH_SHORT).show()
    }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(Intent.createChooser(intent, "Selecciona un archivo PDF"), PICK_PDF_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedPdfUri = data.data
            if (selectedPdfUri != null) {
                Toast.makeText(this, "Archivo seleccionado: ${selectedPdfUri!!.lastPathSegment}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No se seleccionó ningún archivo", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
