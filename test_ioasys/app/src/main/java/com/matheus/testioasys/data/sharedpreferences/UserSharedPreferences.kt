package com.matheus.testioasys.data.sharedpreferences
import android.content.Context
import androidx.core.content.edit
import com.matheus.testioasys.data.dao.UserAuthDAO

import com.matheus.testioasys.data.model.UserAuthData
import com.matheus.testioasys.extensions.fromJson
import com.matheus.testioasys.extensions.toJson

class UserSharedPreferences(context: Context): UserAuthDAO {

    private val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    var authData: UserAuthData?
        get() = sharedPreferences.getString(AUTH_DATA_KEY, null)?.fromJson(UserAuthData::class.java)
        set(value) {
            sharedPreferences.edit(true) {
                putString(AUTH_DATA_KEY, value?.toJson())
            }
        }

    companion object {
        private const val SHARED_PREFERENCES_NAME = "user_preferences"
        private const val AUTH_DATA_KEY = "auth_data"
    }

    override suspend fun getUserAuthData(): UserAuthData? = authData

    override suspend fun saveUserAuthData(userAuthData: UserAuthData) {
        authData = userAuthData
    }
}