package com.matheus.testioasys.data.repository

import com.matheus.testioasys.data.model.UserAuthData

interface LocalAuthProvider {

    suspend fun saveAuthData(userAuthData: UserAuthData)

    suspend fun loadAuthData(): UserAuthData?
}