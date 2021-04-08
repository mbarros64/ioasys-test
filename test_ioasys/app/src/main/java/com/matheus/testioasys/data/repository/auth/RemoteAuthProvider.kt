package com.matheus.testioasys.data.repository.auth

import com.matheus.testioasys.data.model.UserAuthData

interface RemoteAuthProvider {

    suspend fun auth(email: String, password: String): UserAuthData
}