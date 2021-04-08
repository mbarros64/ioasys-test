package com.matheus.testioasys.data.repository

import com.matheus.testioasys.data.model.UserAuthData


interface AuthRepository {

    suspend fun auth(email: String, password: String): UserAuthData

    suspend fun saveAuthData(userAuthData: UserAuthData)

    fun loadAuthData(): UserAuthData?
}