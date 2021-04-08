package com.matheus.testioasys.data.repository.auth

import com.matheus.testioasys.data.model.UserAuthData
import com.matheus.testioasys.data.repository.AuthRepository

class UserAuthRepository(
    private val remoteProvider: RemoteAuthProvider,
    private val localProvider: LocalAuthProvider
) : AuthRepository {

    override suspend fun auth(email: String, password: String): UserAuthData =
        remoteProvider.auth(email, password)

    override suspend fun saveAuthData(userAuthData: UserAuthData) =
        localProvider.saveAuthData(userAuthData)

    override fun loadAuthData(): UserAuthData? = localProvider.loadAuthData()
}