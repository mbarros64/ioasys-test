package com.matheus.testioasys.data.repository.auth

import com.matheus.testioasys.data.auth.UserAuth
import com.matheus.testioasys.data.model.UserAuthData
import com.matheus.testioasys.data.repository.auth.RemoteAuthProvider

class RemoteUserAuth(private val userAuth: UserAuth): RemoteAuthProvider {

    override suspend fun auth(email: String, password: String): UserAuthData = userAuth.doAuth(email, password)
}