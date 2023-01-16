package com.smallmeliapp.model

import com.google.gson.annotations.SerializedName

data class ProductAttributeModel(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("value_name") val valueName: String?,
)
