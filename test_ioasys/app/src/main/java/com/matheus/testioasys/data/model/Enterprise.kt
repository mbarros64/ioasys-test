package com.matheus.testioasys.data.model

import com.google.gson.annotations.SerializedName

data class Enterprise(
    val id: Int,
    @SerializedName("enterprise_name") val enterpriseName: String,
    val description: String,
    var photo: String,
    val city: String,
    val country: String,
    @SerializedName("enterprise_type") val enterpriseType: EnterpriseType
)