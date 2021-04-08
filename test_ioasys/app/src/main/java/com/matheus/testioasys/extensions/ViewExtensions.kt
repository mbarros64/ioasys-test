package com.matheus.testioasys.extensions

import android.view.View
import com.matheus.testioasys.util.GlobalHelper

inline fun View.onClickListener(delay: Long = 1000L, crossinline action: View.() -> Unit) {
    setOnClickListener {
        isEnabled = false
        postDelayed({ isEnabled = true }, delay)
        action(this)
    }
}

inline fun View.globalSafeClickListener(crossinline action: View.() -> Unit) {
    setOnClickListener {
        if (GlobalHelper.canClick()) {
            action()
        }
    }
}