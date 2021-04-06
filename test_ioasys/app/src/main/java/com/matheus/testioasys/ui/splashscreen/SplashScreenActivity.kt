package com.matheus.testioasys.ui.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.matheus.testioasys.databinding.ActivitySplashScreenBinding
import com.matheus.testioasys.ui.setTranslucentWindowControls
import com.matheus.testioasys.ui.setupFullScreenSystemUiFlags

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupFullScreenSystemUiFlags()
        setTranslucentWindowControls(true, true)
    }
}