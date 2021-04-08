package com.matheus.testioasys.ui.main

import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.matheus.testioasys.R
import com.matheus.testioasys.databinding.ActivityMainBinding
import com.matheus.testioasys.extensions.setTranslucentWindowControls
import com.matheus.testioasys.extensions.setupFullScreenSystemUiFlags
import com.matheus.testioasys.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewBiding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBiding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBiding.root)

        setupFullScreenSystemUiFlags()
        setTranslucentWindowControls(false, true)
        navigateToSearchScreen()
    }

    private fun navigateToSearchScreen() {
        supportFragmentManager.commit {
            add(viewBiding.container.id, SearchFragment.newInstance())
        }
    }
}