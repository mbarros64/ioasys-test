package com.matheus.testioasys.data.auth

import com.matheus.testioasys.data.model.UserAuthData

interface UserAuth {

    suspend fun doAuth(email: String, password: String): UserAuthData?
}