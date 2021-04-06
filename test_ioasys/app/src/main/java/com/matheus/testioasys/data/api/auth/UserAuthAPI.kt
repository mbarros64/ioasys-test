package com.matheus.testioasys.data.api.auth

import com.google.gson.GsonBuilder
import com.matheus.testioasys.data.auth.AuthException
import com.matheus.testioasys.data.model.AuthBodyRequest
import com.matheus.testioasys.data.model.UserAuthData
import com.matheus.testioasys.data.auth.UserAuth
import com.matheus.testioasys.data.api.APIConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserAuthAPI : UserAuth {

    private val userAuthService: UserAuthService = Retrofit.Builder()
        .baseUrl("${APIConstants.BASE_URL}/api/${APIConstants.API_VERSION}/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()
        .create(UserAuthService::class.java)

    override suspend fun doAuth(email: String, password: String): UserAuthData? =
        withContext(Dispatchers.IO) {
            val response = userAuthService.signIn(AuthBodyRequest(email, password))
            val authResponse = response.body() ?: run {
                throw AuthException.DefaultAuthException()
            }

            if (authResponse.success) {
                val accessToken = response.headers().get(APIConstants.ACCESS_TOKEN_KEY) ?: run {
                    throw AuthException.DefaultAuthException()
                }
                val uid = response.headers().get(APIConstants.UID_KEY) ?: run {
                    throw AuthException.DefaultAuthException()
                }
                val client = response.headers().get(APIConstants.CLIENT_KEY) ?: run {
                    throw AuthException.DefaultAuthException()
                }

                UserAuthData(accessToken, client, uid)
            } else {
                throw AuthException.InvalidCredentialsException(authResponse.errors?.firstOrNull())
            }
        }
}