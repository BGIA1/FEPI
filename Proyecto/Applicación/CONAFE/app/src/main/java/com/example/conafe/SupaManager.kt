package com.example.logina

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable


@Serializable
data class Usuario(
    var correo: String?=null,
    var tipo_usuario: String?=null,
    var nombre: String?=null,
    var apellidos: String?=null,
    var fecha_nacimiento: String?=null,
    var contraseña: String?=null
)

var supabase:SupabaseClient?=null

fun initSupaClient()
{
    supabase = createSupabaseClient(
        supabaseUrl = "https://cqvlrkpsafdazotfprgz.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNxdmxya3BzYWZkYXpvdGZwcmd6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzYzMDUwODUsImV4cCI6MjA1MTg4MTA4NX0.s0HUZhvxsPZxWvv2aAsz5RF_yPmUPdauSbEktDGGE6Y"
    ) {
        println("CREANDO CLIENTE SUPABASE...")
        install(Postgrest)
    }
}

fun login(correo: String,contraseña: String,callback: (String) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            println("CONSULTANDO USUARIOS")
            val resp = supabase?.from("usuarios")
                ?.select(columns = Columns.list("tipo_usuario")) {
                    filter {
                        and {
                            Usuario::correo eq correo
                            Usuario::contraseña eq contraseña
                        }
                    }
                }?.decodeSingle<Usuario>()

            val tipo = resp?.tipo_usuario

            println(tipo)

            if (tipo != null) {
                callback(tipo)
            }

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                // Maneja errores
                println("Error: ${e.message}")
            }
        }
    }
}