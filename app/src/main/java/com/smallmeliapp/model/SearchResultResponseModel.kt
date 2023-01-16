package com.smallmeliapp.model

import com.google.gson.annotations.SerializedName

data class SearchResultResponseModel(
    @SerializedName("code") val code: Int,
    @SerializedName("body") val body: ProductItemDetailModel,
)
