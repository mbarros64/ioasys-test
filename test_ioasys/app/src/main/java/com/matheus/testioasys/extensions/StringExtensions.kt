package com.matheus.testioasys.extensions

import android.util.Patterns
import com.google.gson.GsonBuilder

fun <T> String.fromJson(className: Class<T>): T = GsonBuilder().create().fromJson(this, className)

fun String.isEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()