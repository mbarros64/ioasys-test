package com.matheus.testioasys.ui.signin

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.matheus.testioasys.R
import com.matheus.testioasys.data.auth.AuthException
import com.matheus.testioasys.databinding.ActivitySignInBinding
import com.matheus.testioasys.extensions.globalSafeClickListener
import com.matheus.testioasys.extensions.hideKeyboard
import com.matheus.testioasys.extensions.setTranslucentWindowControls
import com.matheus.testioasys.extensions.setupFullScreenSystemUiFlags
import com.matheus.testioasys.ui.main.MainActivity
import com.matheus.testioasys.util.RequestState
import eightbitlab.com.blurview.RenderScriptBlur
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySignInBinding

    private val viewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupFullScreenSystemUiFlags()
        setTranslucentWindowControls(
                navigationBarColor = ContextCompat.getColor(
                        baseContext,
                        R.color.colorDefaultNavigationBar
                ), withLightStatusBar = true, withLightNavigationBar = true
        )

        setupRequestUpdate()
        setupPasswordEditTextToggleButton()
        setupEmailTextChanged()
        setupPasswordTextChanged()
        setupEnterButton()
    }

    private fun setupRequestUpdate() {
        viewModel.requestState.observe(this, {
            when (it) {
                RequestState.LOADING -> {
                    showLoading()
                }
                RequestState.SUCCESS -> onSuccessSignIn()
                is RequestState.FAILED -> {
                    hideLoading()

                    val messageRes = when (it.exception) {
                        is AuthException.InvalidCredentialsException -> {
                            disableEnterButton()
                            R.string.error_invalid_credentials
                        }
                        is AuthException.InvalidEmailException -> {
                            showEmailError()
                            null
                        }
                        is AuthException.InvalidPasswordException -> {
                            showPasswordError()
                            null
                        }
                        else -> R.string.error_some_error_occurred
                    }

                    messageRes?.let { stringRes ->
                        showError(getString(stringRes))
                    }
                }
            }
        })
    }

    private fun setupPasswordEditTextToggleButton() {
        viewBinding.passwordInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
    }

    private fun setupEmailTextChanged() {
        viewBinding.emailInput.doAfterTextChanged {
            if (viewBinding.emailInputLayout.error?.isNotEmpty() == true) {
                clearError()
            }
        }
    }

    private fun setupPasswordTextChanged() {
        viewBinding.passwordInput.doAfterTextChanged {
            if (viewBinding.passwordInputLayout.error?.isNotEmpty() == true) {
                clearError()
            }
        }
    }

    private fun setupEnterButton() {
        viewBinding.enterButton.globalSafeClickListener {
            performAuthentication()
        }
    }

    private fun setupBlur() {
        val decorView = window.decorView as ViewGroup

        viewBinding.blurView.setupWith(decorView).apply {
            setBlurAlgorithm(RenderScriptBlur(this@SignInActivity))
            setBlurRadius(.5f)
            setBlurEnabled(true)
        }
    }

    private fun performAuthentication() {
        closeKeyboard()
        viewBinding.apply {
            val email = emailInput.text?.toString() ?: ""
            val password = passwordInput.text?.toString() ?: ""

            viewModel.authenticate(email, password)
        }
    }

    private fun clearError() {
        viewBinding.apply {
            showEmailError(null)
            showPasswordError(null)
            errorText.alpha = 0f
            enterButton.isEnabled = true
            enableEnterButton()
        }
    }

    private fun showError(message: String) {
        viewBinding.apply {
            showEmailError(message)
            showPasswordError(message)
            errorText.apply {
                alpha = 1f
                text = message
            }
        }
    }

    private fun showEmailError(message: String? = "error") {
        viewBinding.emailInputLayout.error = message
    }

    private fun showPasswordError(message: String? = "error") {
        viewBinding.passwordInputLayout.error = message
    }

    private fun onSuccessSignIn() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showLoading() {
        setupBlur()
        viewBinding.loading.isVisible = true
    }

    private fun hideLoading() {
        viewBinding.loading.isVisible = false
    }

    private fun closeKeyboard() {
        hideKeyboard()
        viewBinding.root.requestFocus()
    }

    private fun disableEnterButton() {
        viewBinding.enterButton.isEnabled = false
    }

    private fun enableEnterButton() {
        viewBinding.enterButton.isEnabled = true
    }
}