package com.matheus.testioasys.data.repository.enterprise

import com.matheus.testioasys.data.model.Enterprise

class EnterprisesRepository(private val enterpriseProvider: RemoteEnterpriseProvider) : EnterpriseRepository {

    override suspend fun search(query: String): List<Enterprise> = enterpriseProvider.search(query)
}