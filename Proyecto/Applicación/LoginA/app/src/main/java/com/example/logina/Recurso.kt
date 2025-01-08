package com.example.logina

data class Recurso(
    val zona: String,
    val recurso: String,
    var estatus: String // Pendiente, Aceptado, Rechazado
)
