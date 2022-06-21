package com.neo_orez.PodcastAppGraphql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val bottomnavigation = findViewById<BottomNavigationView>(R.id.bottomNavViewID)
        val navcontroller  = findNavController(R.id.fragmentContainerView)
        val appbarConfig = AppBarConfiguration(setOf(R.id.firsFragment, R.id.secondFragment, R.id.mainFragment))
        setupActionBarWithNavController(navcontroller, appbarConfig)

        bottomnavigation.setupWithNavController(navcontroller)
    }
}