package com.matheus.testioasys.data.model

import com.google.gson.annotations.SerializedName

data class EnterpriseType(
    val id: Int,
    @SerializedName("enterprise_type_name") val enterpriseTypeName: String
)