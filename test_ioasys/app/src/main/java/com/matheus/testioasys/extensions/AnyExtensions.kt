package com.matheus.testioasys.extensions

import com.google.gson.GsonBuilder

fun <T> T.toJson(): String = GsonBuilder().create().toJson(this)
