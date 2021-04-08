package com.matheus.testioasys.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EnterpriseType(
    val id: Int,
    @SerializedName("enterprise_type_name") val enterpriseTypeName: String
) : Parcelable