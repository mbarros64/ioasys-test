package com.matheus.testioasys.data.api.auth

import com.matheus.testioasys.data.model.AuthBodyRequest
import com.matheus.testioasys.data.model.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthService {

    @POST("users/auth/sign_in")
    suspend fun signIn(@Body bodyRequest: AuthBodyRequest): Response<AuthResponse>
}