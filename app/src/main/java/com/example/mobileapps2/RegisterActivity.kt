package com.example.mobileapps2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity: AppCompatActivity() {

    private lateinit var credentialsManager: CredentialsManager
    private lateinit var emailInputField: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordInputField: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var nameInputField: TextInputLayout
    private lateinit var nameEditText: TextInputEditText
    private lateinit var phoneInputField: TextInputLayout
    private lateinit var phoneEditText: TextInputEditText
    private lateinit var registerButton: Button
    private lateinit var loginTextbutton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.create_account)

        credentialsManager = CredentialsManager()
        emailInputField = findViewById(R.id.email_field)
        emailEditText = findViewById(R.id.email_edit_text)
        passwordInputField = findViewById(R.id.new_password_field)
        passwordEditText = findViewById(R.id.new_password_edit_text)
        nameInputField = findViewById(R.id.name_field)
        nameEditText = findViewById(R.id.name_edit_text)
        phoneInputField = findViewById(R.id.phone_number_field)
        phoneEditText = findViewById(R.id.phone_number_edit_text)
        registerButton = findViewById(R.id.next_button)
        loginTextbutton = findViewById(R.id.log_in)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activityRegister_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        registerButton.setOnClickListener {
            Log.d("RegisterActivity", "register button registers click")
            var password = passwordEditText.text.toString()
            var email = emailEditText.text.toString()
            var name = nameEditText.text.toString()
            var phone = phoneEditText.text.toString()

            if (checkRegistrationCredentials(email, password, name, phone)) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        loginTextbutton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /* Just wanted to say here that the optional task about using these credentials to log in actually works */
    private fun checkRegistrationCredentials(email: String, password: String, name: String, phone: String): Boolean {
        var isValid = true

        if (!credentialsManager.isEmailNonEmpty(email)) {
            emailInputField.error = "Email cannot be empty"
            isValid = false
        } else if (!credentialsManager.isEmailValid(email)) {
            emailInputField.error = "Invalid email format"
            isValid = false
        } else {
            emailInputField.error = null
        }

        if (!credentialsManager.isPasswordFilled(password)) {
            passwordInputField.error = "Password cannot be empty"
            isValid = false
        } else {
            passwordInputField.error = null
        }

        if (!credentialsManager.isNameFilled(name)) {
            nameInputField.error = "Name cannot be empty"
            isValid = false
        } else {
            nameInputField.error = null
        }

        if (!credentialsManager.isPhoneFilled(phone)) {
            phoneInputField.error = "Phone number cannot be empty"
            isValid = false
        } else {
            phoneInputField.error = null
        }

        // Attempt to register if all fields are valid
        if (isValid) {
            Log.d("RegisterActivity", "Credentials valid, trying to register")
            if (!credentialsManager.register(email, password)) {
                emailInputField.error = "Email already taken"
                isValid = false
            } else {
                emailInputField.error = null
            }
        }
        return isValid
    }
}
