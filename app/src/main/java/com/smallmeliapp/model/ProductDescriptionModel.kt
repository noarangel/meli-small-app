package com.smallmeliapp.model

import com.google.gson.annotations.SerializedName

data class ProductDescriptionModel(
    @SerializedName("text") val text: String?,
    @SerializedName("plain_text") val plainText: String,
    @SerializedName("last_updated") val lastUpdated: String?,
    @SerializedName("date_created") val date_created: String?,
)
