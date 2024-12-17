package com.example.mobileapps2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log

class FragmentB : Fragment(R.layout.fragment_b) {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        Log.d("FragmentB","Fragment B created")
    }

    override fun onResume(){
        super.onResume()
        Log.d("FragmentB","Fragment B resumed")
    }
}