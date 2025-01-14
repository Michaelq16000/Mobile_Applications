package com.example.mobileapps2

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

class RegisterFragment : Fragment() {

    private lateinit var emailInputField: TextInputLayout
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordInputField: TextInputLayout
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var nameInputField: TextInputLayout
    private lateinit var nameEditText: TextInputEditText
    private lateinit var phoneInputField: TextInputLayout
    private lateinit var phoneEditText: TextInputEditText
    private lateinit var registerButton: Button
    private lateinit var loginTextButton: TextView
    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.create_account, container, false)
        credentialsManager = (activity as FragmentedAuthentication).credentialsManager

        emailInputField = view.findViewById(R.id.email_field)
        emailEditText = view.findViewById(R.id.email_edit_text)
        passwordInputField = view.findViewById(R.id.new_password_field)
        passwordEditText = view.findViewById(R.id.new_password_edit_text)
        nameInputField = view.findViewById(R.id.name_field)
        nameEditText = view.findViewById(R.id.name_edit_text)
        phoneInputField = view.findViewById(R.id.phone_number_field)
        phoneEditText = view.findViewById(R.id.phone_number_edit_text)
        registerButton = view.findViewById(R.id.next_button)
        loginTextButton = view.findViewById(R.id.log_in)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()

            if (checkRegistrationCredentials(email, password, name, phone)) {
                Log.d("RegisterFragment", "Registration successful")
                (activity as FragmentedAuthentication).navigateToLoginFragment()
            }
        }

        loginTextButton.setOnClickListener {
            (activity as FragmentedAuthentication).navigateToLoginFragment()
        }

        return view
    }

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

        if (isValid && !credentialsManager.register(email, password)) {
            emailInputField.error = "Email already taken"
            isValid = false
        }

        return isValid
    }
}
