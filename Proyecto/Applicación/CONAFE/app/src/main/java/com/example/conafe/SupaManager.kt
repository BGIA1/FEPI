package com.example.conafe

import android.content.Context
import android.widget.Toast
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.Serializable
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonObject



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

fun actualizarRegistro(tabla: String, camposActualizar: List<Pair<String,String>>, filtros: List<Pair<String, String>>) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            println("Actualizando $tabla")
            supabase?.from(tabla)
                ?.update({
                    camposActualizar.forEach{(campoActualizar,valorActualizado)->
                        set(campoActualizar, valorActualizado)
                    }
                })
                {
                    filter {
                        and {
                            filtros.forEach { (campoFiltro, filtro) ->
                                eq(campoFiltro, filtro)
                            }
                        }
                    }
                }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                // Manejar errores
                println("Error al actualizar $tabla: ${e.message}")
            }
        }
    }
}

fun consultaJSON(tabla: String, campos: String, filtros: List<Pair<String, String>>,
                 callback: (String?,Boolean) -> Unit)
{
    CoroutineScope(Dispatchers.IO).launch {
        try {
            println("Consultando $tabla")
            val jsonResp = supabase?.from(tabla)
                ?.select(columns = Columns.raw(campos)) {
                    if (filtros.isNotEmpty()) {
                        filter {
                            and {
                                filtros.forEach { (campoFiltro, filtro) ->
                                    eq(campoFiltro, filtro)
                                }
                            }
                        }
                    }
                }?.data

            if (jsonResp != null) {
                    callback(jsonResp,true)
            } else {
                withContext(Dispatchers.Main) {
                    println("Error: La respuesta es nula.")
                    callback(null, false) // Operación fallida
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                // Manejar errores
                println("Error: ${e.message}")
                callback(null, false) // Operación fallida
            }
        }
    }
}


fun consulta(tabla: String, campos: String, filtros: List<Pair<String, String>>,
             callback: (List<Map<String, Any?>>?,Boolean) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            println("Consultando $tabla")
            val jsonResp = supabase?.from(tabla)
                ?.select(columns = Columns.raw(campos)) {
                    if (filtros.isNotEmpty()) {
                        filter {
                            and {
                                filtros.forEach { (campoFiltro, filtro) ->
                                    eq(campoFiltro, filtro)
                                }
                            }
                        }
                    }
                }?.data

            if (jsonResp != null) {
                val jsonElement = Json.parseToJsonElement(jsonResp)
                if (jsonElement is List<*>) {
                    val result = jsonElement.mapNotNull { element ->
                        if (element is JsonObject) {
                            element.toMap()
                        } else {
                            null
                        }
                    }
                    callback(result,true)
                } else {
                    withContext(Dispatchers.Main) {
                        println("Error: La respuesta no es una lista de objetos JSON.")
                        callback(null, false) // Operación fallida
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    println("Error: La respuesta es nula.")
                    callback(null, false) // Operación fallida
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                // Manejar errores
                println("Error: ${e.message}")
                callback(null, false) // Operación fallida
            }
        }
    }
}

fun JsonObject.toMap(): Map<String, Any?> {
    val map = mutableMapOf<String, Any?>()
    for (entry in this.entries) {
        val value = when (val jsonElement = entry.value) {
            is JsonPrimitive -> when {
                jsonElement.isString -> jsonElement.content
                jsonElement.booleanOrNull != null -> jsonElement.booleanOrNull
                jsonElement.intOrNull != null -> jsonElement.intOrNull
                jsonElement.floatOrNull != null -> jsonElement.floatOrNull
                else -> null
            }
            else -> null
        }
        map[entry.key] = value
    }
    return map
}
