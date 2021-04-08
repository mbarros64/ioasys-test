package com.matheus.testioasys.extensions

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

fun Activity.setupFullScreenSystemUiFlags() {
    window.decorView.apply {
        systemUiVisibility =
            systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }

    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
}

fun Activity.setStatusBarColor(color: Int, withLightStatusBar: Boolean = false) {
    var flags = window.decorView.systemUiVisibility

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        flags = if (withLightStatusBar) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }

    window.decorView.systemUiVisibility = flags
    window.statusBarColor = color
}

fun Activity.setNavigationBarColor(color: Int, withLightNavigationBar: Boolean = false) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val flags = window.decorView.systemUiVisibility
        window.decorView.systemUiVisibility = if (withLightNavigationBar) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        }
    }

    window.navigationBarColor = color
}

fun Activity.setTranslucentWindowControls(
    withLightStatusBar: Boolean = false,
    withLightNavigationBar: Boolean = false
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        setStatusBarColor(Color.TRANSPARENT, withLightStatusBar)
        setNavigationBarColor(Color.TRANSPARENT, withLightNavigationBar)
    } else {
        setTranslucentStatusBar()
        setTranslucentNavigationBar()
    }
}

fun Activity.setTranslucentStatusBar() {
    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
}

fun Activity.setTranslucentNavigationBar() {
    val addFlags = window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

}

fun Activity.hideKeyboard() {
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let { inputMethodManager ->
        val focusedView = currentFocus ?: View(this)

        inputMethodManager.hideSoftInputFromWindow(focusedView.windowToken, 0)
    }
}