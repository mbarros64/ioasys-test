package com.matheus.testioasys.data.repository

import com.matheus.testioasys.data.dao.UserAuthDAO
import com.matheus.testioasys.data.model.UserAuthData

class LocalUserAuth(private val userAuthDAO: UserAuthDAO): LocalAuthProvider {

    override suspend fun saveAuthData(userAuthData: UserAuthData) = userAuthDAO.saveUserAuthData(userAuthData)

    override suspend fun loadAuthData(): UserAuthData? = userAuthDAO.getUserAuthData()
}