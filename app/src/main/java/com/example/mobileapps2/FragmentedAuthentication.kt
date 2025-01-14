package com.example.mobileapps2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FragmentedAuthentication : AppCompatActivity() {
    lateinit var credentialsManager: CredentialsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragmented_authentication_layout)

        credentialsManager = CredentialsManager()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, LoginFragment())
                .commit()
        }
    }

    fun navigateToRegisterFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, RegisterFragment())
            .addToBackStack(null)
            .commit()
    }

    fun navigateToLoginFragment() {
        supportFragmentManager.popBackStack()
    }
}