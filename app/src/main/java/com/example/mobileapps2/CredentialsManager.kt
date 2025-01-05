// Put your package name here. Check your activity for reference.
package com.example.mobileapps2

class CredentialsManager {
    fun isEmailNonEmpty(email: String) : Boolean {
        return email.isNotEmpty()
    }
    fun isEmailValid(email: String): Boolean {
        val emailPattern = "^[a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+){1,5}$"
        return Regex(emailPattern).matches(email)
    }
}