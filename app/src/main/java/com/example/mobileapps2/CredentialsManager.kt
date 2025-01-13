// Put your package name here. Check your activity for reference.
package com.example.mobileapps2

class CredentialsManager {

    private val accounts = mutableMapOf<String, String>()

    fun isEmailNonEmpty(email: String) : Boolean {
        return email.isNotEmpty()
    }

    fun isEmailValid(email: String): Boolean {
        val emailPattern = "^[a-zA-Z0-9!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+){1,5}$"
        return Regex(emailPattern).matches(email)
    }

    fun isPasswordFilled(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun isNameFilled(name: String): Boolean {
        return name.isNotEmpty()
    }

    fun isPhoneFilled(phone: String): Boolean {
        return phone.isNotEmpty()
    }

    fun register(tempEmail: String, password: String): Boolean {
        val email = tempEmail.lowercase()

        if (accounts.containsKey(email)) return false
        accounts[email] = password
        return true
    }
}