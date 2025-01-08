package com.example.mobileapps2

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private lateinit var credentialsManager: CredentialsManager
    private lateinit var emailInputField: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordInputField: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_account)

        emailInputField = findViewById(R.id.email_field)
        emailEditText = findViewById(R.id.email_edit_text)
        passwordInputField = findViewById(R.id.password_field)
        passwordEditText = findViewById(R.id.password_edit_text)
        loginButton = findViewById(R.id.next_button)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activityLogin_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            checkCredentials(email, password)
        }

    }

    private fun checkCredentials(email: String, password: String) {
        if (!credentialsManager.isEmailNonEmpty(email)) {
            emailInputField.error = "Email cannot be empty"
        } else if (!credentialsManager.isEmailValid(email)) {
            emailInputField.error = "Invalid email format"
        } else {
            emailInputField.error = null
        }
        if (!credentialsManager.isPasswordFilled(password)) {
            passwordInputField.error = "Password cannot be empty"
        } else {
            passwordInputField.error = null
        }

    }

}