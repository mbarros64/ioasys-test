package com.matheus.testioasys.ui.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.matheus.testioasys.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}