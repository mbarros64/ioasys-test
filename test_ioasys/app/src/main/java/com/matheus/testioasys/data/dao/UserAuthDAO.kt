package com.matheus.testioasys.data.dao

import com.matheus.testioasys.data.model.UserAuthData

interface UserAuthDAO {

    suspend fun getUserAuthData(): UserAuthData?

    suspend fun saveUserAuthData(userAuthData: UserAuthData)
}