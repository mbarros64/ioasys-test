package com.matheus.testioasys.data.repository

import com.matheus.testioasys.data.auth.UserAuth
import com.matheus.testioasys.data.model.UserAuthData

class RemoteUserAuth(private val userAuth: UserAuth): RemoteAuthProvider {

    override suspend fun auth(email: String, password: String): UserAuthData = userAuth.doAuth(email, password)
}