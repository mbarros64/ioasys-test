package com.matheus.testioasys.data.api.enterprise

import com.matheus.testioasys.data.model.Enterprise
import com.matheus.testioasys.data.model.SearchEnterpriseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EnterpriseService {

    @GET("enterprises")
    suspend fun search(
        @Query(
            value = "name",
            encoded = true
        ) query: String
    ): SearchEnterpriseResponse

    @GET("enterprises/{id}")
    suspend fun show(@Path(value = "id") id: Int): Enterprise
}