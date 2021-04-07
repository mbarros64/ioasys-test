package com.matheus.testioasys.data.repository

import com.matheus.testioasys.data.model.UserAuthData

interface UserAuthRepository {

    suspend fun auth(email: String, password: String): UserAuthData

    suspend fun saveAuthData(userAuthData: UserAuthData)

    suspend fun loadAuthData(): UserAuthData?
}