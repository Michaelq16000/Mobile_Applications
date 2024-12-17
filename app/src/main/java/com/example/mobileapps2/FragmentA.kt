package com.example.mobileapps2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log

class FragmentA : Fragment(R.layout.fragment_a) {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        Log.d("FragmentA","Fragment A created")
    }

    override fun onResume(){
        super.onResume()
        Log.d("FragmentA","Fragment A resumed")
    }
}