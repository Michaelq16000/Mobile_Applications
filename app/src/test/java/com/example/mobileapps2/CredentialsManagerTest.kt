// Put your package name here. Check your activity for reference.
package com.example.mobileapps2

import org.junit.Test
import org.junit.Assert.*

class CredentialsManagerTest {

    // Test empty email
    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isEmailNonEmpty = credentialsManager.isEmailNonEmpty("")
        assertEquals(false, isEmailNonEmpty)
    }

    // Test wrong email format
    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val credentialsManager = CredentialsManager()
        val isEmailValid = credentialsManager.isEmailValid("w4w4w33w4#@.gmail.c#m")
        assertEquals(false, isEmailValid)
    }
    // Test proper email

    // Test empty password
    // Test filled password
}