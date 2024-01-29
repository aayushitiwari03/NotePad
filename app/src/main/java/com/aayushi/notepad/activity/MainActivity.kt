package com.aayushi.notepad.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.aayushi.notepad.R
import com.aayushi.notepad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_graph) as NavHostFragment
        navHostFragment.navController.navigate(R.id.displayNotesFragment)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}