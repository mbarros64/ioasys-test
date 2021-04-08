package com.matheus.testioasys.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.commitNow
import com.matheus.testioasys.R
import com.matheus.testioasys.databinding.ActivityMainBinding
import com.matheus.testioasys.extensions.setTranslucentWindowControls
import com.matheus.testioasys.extensions.setupFullScreenSystemUiFlags
import com.matheus.testioasys.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupFullScreenSystemUiFlags()
        setTranslucentWindowControls(
                navigationBarColor = ContextCompat.getColor(
                        baseContext,
                        R.color.colorDefaultNavigationBar
                ), withLightStatusBar = false, withLightNavigationBar = true
        )
        navigateToSearchScreen()
    }

    private fun navigateToSearchScreen() {
        supportFragmentManager.commitNow {
            add(viewBinding.container.id, SearchFragment.newInstance())
        }
    }
}