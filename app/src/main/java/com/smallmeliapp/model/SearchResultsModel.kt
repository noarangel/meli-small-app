package com.smallmeliapp.model

import com.google.gson.annotations.SerializedName

data class SearchResultsModel(
    @SerializedName("site_id") val siteId: String,
    @SerializedName("query") val query: String,
    @SerializedName("paging") val paging: PaginationModel,
    @SerializedName("results") val results: List<SearchResultItemModel>,
)
