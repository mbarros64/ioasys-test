package com.matheus.testioasys.extensions

import com.google.gson.GsonBuilder

fun <T> String.fromJson(className: Class<T>): T = GsonBuilder().create().fromJson(this, className)
