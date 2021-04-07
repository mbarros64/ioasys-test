package com.matheus.testioasys.data.repository

import com.matheus.testioasys.data.model.UserAuthData

class AuthRepository(
        private val remoteProvider: RemoteAuthProvider,
        private val localProvider: LocalAuthProvider
) : UserAuthRepository {

    override suspend fun auth(email: String, password: String): UserAuthData = remoteProvider.auth(email, password)

    override suspend fun saveAuthData(userAuthData: UserAuthData) = localProvider.saveAuthData(userAuthData)

    override suspend fun loadAuthData(): UserAuthData? = localProvider.loadAuthData()
}