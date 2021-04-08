package com.matheus.testioasys.data.repository.auth

import com.matheus.testioasys.data.dao.UserAuthDAO
import com.matheus.testioasys.data.model.UserAuthData

class LocalUserAuth(private val userAuthDAO: UserAuthDAO): LocalAuthProvider {

    override fun saveAuthData(userAuthData: UserAuthData) = userAuthDAO.saveUserAuthData(userAuthData)

    override fun loadAuthData(): UserAuthData? = userAuthDAO.getUserAuthData()
}