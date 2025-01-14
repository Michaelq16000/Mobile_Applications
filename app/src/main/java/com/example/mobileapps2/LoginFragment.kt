package com.example.mobileapps2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import android.widget.Button
import android.widget.TextView

class LoginFragment : Fragment() {

    private lateinit var credentialsManager: CredentialsManager
    private lateinit var emailInputField: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordInputField: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var registerNowTextbutton: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_account, container, false)
        credentialsManager = (activity as FragmentedAuthentication).credentialsManager

        emailInputField = view.findViewById(R.id.email_field)
        emailEditText = view.findViewById(R.id.email_edit_text)
        passwordInputField = view.findViewById(R.id.password_field)
        passwordEditText = view.findViewById(R.id.password_edit_text)
        loginButton = view.findViewById(R.id.next_button)
        registerNowTextbutton = view.findViewById(R.id.register_now)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (checkCredentials(email, password)) {
                Log.d("LoginFragment", "Credentials OK")
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }

        registerNowTextbutton.setOnClickListener {
            (activity as FragmentedAuthentication).navigateToRegisterFragment()
        }
        return view
    }

    private fun checkCredentials(email: String, password: String): Boolean {
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
        Log.d("LoginFragment", "Credentials checked")
        return (emailInputField.error == null && passwordInputField.error == null)
    }
}