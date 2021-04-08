package com.matheus.testioasys.util

object GlobalHelper {
    private const val CLICK_DELAY = 500L
    private var lastClickTime = 0L

    fun canClick(): Boolean {
        val currentTime = System.currentTimeMillis()

        return (currentTime - lastClickTime >= CLICK_DELAY).also {
            if (it) {
                lastClickTime = currentTime
            }
        }
    }
}