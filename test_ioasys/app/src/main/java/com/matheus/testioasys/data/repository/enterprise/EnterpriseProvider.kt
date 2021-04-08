package com.matheus.testioasys.data.repository.enterprise

import com.matheus.testioasys.data.dao.EnterpriseDAO
import com.matheus.testioasys.data.model.Enterprise

class EnterpriseProvider(private val enterpriseDAO: EnterpriseDAO) : RemoteEnterpriseProvider {

    override suspend fun search(query: String): List<Enterprise> = enterpriseDAO.search(query)
}