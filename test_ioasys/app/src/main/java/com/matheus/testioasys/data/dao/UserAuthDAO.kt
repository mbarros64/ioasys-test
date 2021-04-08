package com.matheus.testioasys.data.dao

import com.matheus.testioasys.data.model.UserAuthData

interface UserAuthDAO {

    fun getUserAuthData(): UserAuthData?

    fun saveUserAuthData(userAuthData: UserAuthData)}