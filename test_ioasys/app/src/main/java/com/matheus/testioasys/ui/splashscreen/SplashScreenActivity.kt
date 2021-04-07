package com.matheus.testioasys.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.matheus.testioasys.databinding.ActivitySplashScreenBinding
import com.matheus.testioasys.ui.setTranslucentWindowControls
import com.matheus.testioasys.ui.setupFullScreenSystemUiFlags
import com.matheus.testioasys.ui.signin.SignInActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupFullScreenSystemUiFlags()
        setTranslucentWindowControls(true, true)
        viewBinding.root.postDelayed({
            startActivity(Intent(this, SignInActivity::class.java))
        }, START_DELAY)
    }

    companion object {
        private const val START_DELAY = 1500L
    }
}