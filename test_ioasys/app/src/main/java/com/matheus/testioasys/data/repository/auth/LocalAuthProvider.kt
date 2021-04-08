package com.matheus.testioasys.data.repository.auth

import com.matheus.testioasys.data.model.UserAuthData

interface LocalAuthProvider {

    fun saveAuthData(userAuthData: UserAuthData)

    fun loadAuthData(): UserAuthData?
}