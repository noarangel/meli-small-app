package com.smallmeliapp.model

import com.google.gson.annotations.SerializedName

data class PaginationModel(
    @SerializedName("total") val total: Int,
    @SerializedName("primary_results") val primaryResults: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
)
