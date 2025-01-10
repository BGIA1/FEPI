package com.example.logina.DataBase

data class Usuario(
    val id: Int,
    val nombre: String,
    val apellidos: String,
    val correo: String,
    val fechaNacimiento: String,
    val celular: String,
    val contrasena: String,
    val tipoCuenta: String
)
