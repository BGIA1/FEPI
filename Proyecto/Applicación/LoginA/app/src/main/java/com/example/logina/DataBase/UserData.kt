package com.example.logina.DataBase

object UserData {
    var name: String? = null
    var email: String? = null
    var password: String? = null

    fun clear() {
        name = null
        email = null
        password = null
    }
}
