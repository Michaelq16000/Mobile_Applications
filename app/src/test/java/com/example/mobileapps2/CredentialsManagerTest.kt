// Put your package name here. Check your activity for reference.
package com.example.mobileapps2

import org.junit.Test
import org.junit.Assert.*

class CredentialsManagerTest {
    val credentialsManager = CredentialsManager()
    // Test empty email
    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val isEmailNonEmpty = credentialsManager.isEmailNonEmpty("")
        assertEquals(false, isEmailNonEmpty)
    }

    // Test wrong email format
    @Test
    fun givenWrongEmailFormat_thenReturnFalse() {
        val isEmailValid = credentialsManager.isEmailValid("w4w4w33w4#@.gmail.c#m")
        assertEquals(false, isEmailValid)
    }
    // Test proper email
    @Test
    fun givenValidEmailFormat_thenReturnTrue() {
        val isEmailValid = credentialsManager.isEmailValid("w4w4w33w4#@gmail.com")
        assertEquals(true, isEmailValid)
    }
    // Test empty password
    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val isPasswordFilled = credentialsManager.isPasswordFilled("")
        assertEquals(false, isPasswordFilled)
    }
    // Test filled password
    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val isPasswordFilled = credentialsManager.isPasswordFilled("wazzupimmap4ssw0rd")
        assertEquals(true, isPasswordFilled)
    }
    // Test new email
    @Test
    fun givenNewEmail_thenRegisterSuccessfully() {
        val isEmailNew = credentialsManager.register("newemail@gogl.com", "password123")
        assertEquals(true, isEmailNew)
    }
    // Test registering with an already taken email
    @Test
    fun givenExistingEmail_thenRegisterFails() {
        // Register the first email
        credentialsManager.register("newemail@gogl.com", "password123")
        // Try registering with the same email
        val isEmailNew = credentialsManager.register("newemail@gogl.com", "newpassword")
        assertEquals(false,isEmailNew)
    }
    // Test registering with an already taken email (case insensitive)
    @Test
    fun givenExistingEmailUppercase_thenRegisterFails() {
        // Register the first email
        credentialsManager.register("newemail@gogl.com", "password123")
        // Try registering with the same email
        val isEmailNew = credentialsManager.register("NEWEMAIL@GOGL.COM", "newpassword")
        assertEquals(false,isEmailNew)
    }

}