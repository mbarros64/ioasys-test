package com.matheus.testioasys.extensions

import android.content.Context
import android.util.TypedValue

val Context.actionBarHeight: Int
    get() {
        val value = TypedValue()

        return if (theme.resolveAttribute(android.R.attr.actionBarSize, value, true)) {
            TypedValue.complexToDimensionPixelSize(value.data, resources.displayMetrics)
        } else {
            0
        }
    }