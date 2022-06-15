package com.yongjincompany.popo.ui

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.yongjincompany.popo.R
import com.yongjincompany.popo.base.BaseActivity
import com.yongjincompany.popo.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        

    }


    override fun initView() {
        binding.popularity.setOnClickListener {
            binding.popularity.setTextColor(Color.parseColor("#303f9f"))
            binding.popularity.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22F)
            binding.latest.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18F)
            binding.latest.setTextColor(Color.parseColor("#A3A3A3"))
        }
        binding.latest.setOnClickListener {
            binding.latest.setTextColor(Color.parseColor("#303f9f"))
            binding.latest.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22F)
            binding.popularity.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18F)
            binding.popularity.setTextColor(Color.parseColor("#A3A3A3"))
        }
    }
}