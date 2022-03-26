package com.yongjincompany.onpost

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yongjincompany.onpost.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.background = null
        binding.bottomNavigation.menu.getItem(1).isEnabled = false


    }
}