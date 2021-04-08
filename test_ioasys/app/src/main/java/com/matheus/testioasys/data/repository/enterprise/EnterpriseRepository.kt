package com.matheus.testioasys.data.repository.enterprise

import com.matheus.testioasys.data.model.Enterprise

interface EnterpriseRepository {

    suspend fun search(query: String): List<Enterprise>
}