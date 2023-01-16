package com.smallmeliapp.model

import com.google.gson.annotations.SerializedName

data class ProductPictureModel(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url: String,
    @SerializedName("secure_url") val secureUrl: String,
    @SerializedName("size") val size: String,
    @SerializedName("max_size") val maxSize: String,
    @SerializedName("quality") val category_Id: String?,
)
