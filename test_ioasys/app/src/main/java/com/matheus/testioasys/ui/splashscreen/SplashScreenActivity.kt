package com.matheus.testioasys.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.matheus.testioasys.databinding.ActivitySplashScreenBinding
import com.matheus.testioasys.extensions.setTranslucentWindowControls
import com.matheus.testioasys.extensions.setupFullScreenSystemUiFlags
import com.matheus.testioasys.ui.main.MainActivity
import com.matheus.testioasys.ui.signin.SignInActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashScreenBinding

    private val viewModel: SplashScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupFullScreenSystemUiFlags()
        setTranslucentWindowControls(true, true)
        viewBinding.root.postDelayed({
            startApp()
        }, START_DELAY)
    }

    private fun startApp() {
        viewModel.checkCurrentAuthentication(::onSuccessAuth, ::onFailureAuth)
    }

    private fun onSuccessAuth() {
        startScreen(MainActivity::class.java)
    }

    private fun onFailureAuth() {
        startScreen(SignInActivity::class.java)
    }

    private fun <T> startScreen(className: Class<T>) {
        startActivity(Intent(this, className))
    }

    companion object {
        private const val START_DELAY = 1500L
    }
}