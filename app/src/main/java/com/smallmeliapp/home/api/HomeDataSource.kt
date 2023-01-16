package com.smallmeliapp.home.api

import com.smallmeliapp.core.api.response.ApiResponse
import com.smallmeliapp.model.SearchResultsModel
import com.smallmeliapp.model.SiteModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeDataSource {
    @GET("sites")
    suspend fun getSites(): ApiResponse<List<SiteModel>>

    @GET("sites/{site_id}/search")
    suspend fun getProductsBySearch(
        @Path("site_id") siteID: String,
        @Query("q") findText: String
    ): ApiResponse<SearchResultsModel>
}