package com.example.mobileapps2

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import com.example.mobileapps2.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.core.view.WindowInsetsCompat
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activityMain_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<View>(R.id.switchFragments_button).setOnClickListener {
            supportFragmentManager.commit {
                Log.d("switch_button","Switch button clicked")

                if(savedInstanceState == null) {
                    supportFragmentManager.commit {
                        replace<FragmentA>(R.id.fragment_container_view)
                    }
                }

                var currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)

                if (currentFragment is FragmentA) {
                    replace<FragmentB>(R.id.fragment_container_view)
                    addToBackStack(null)
                }
                else{
                    supportFragmentManager.popBackStack()
                }
            }
        }
    }
}