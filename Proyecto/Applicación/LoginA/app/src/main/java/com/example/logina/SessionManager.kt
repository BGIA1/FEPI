package com.example.logina

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserSession", Context.MODE_PRIVATE)

    fun saveUserSession(userId: Int, userName: String, userEmail: String, userType: String) {
        val editor = sharedPreferences.edit()
        editor.putInt("USER_ID", userId)
        editor.putString("USER_NAME", userName)
        editor.putString("USER_EMAIL", userEmail)
        editor.putString("USER_TYPE", userType)
        editor.apply()
    }

    fun getUserId(): Int = sharedPreferences.getInt("USER_ID", -1)

    fun getUserName(): String? = sharedPreferences.getString("USER_NAME", null)

    fun getUserEmail(): String? = sharedPreferences.getString("USER_EMAIL", null)

    fun getUserType(): String? = sharedPreferences.getString("USER_TYPE", null)

    fun isLoggedIn(): Boolean = sharedPreferences.getInt("USER_ID", -1) != -1

    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }
}
