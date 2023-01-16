package com.smallmeliapp.product.api

import com.smallmeliapp.core.api.response.ApiResponse
import com.smallmeliapp.model.ProductDescriptionModel
import com.smallmeliapp.model.SearchResultResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductDataSource {

    @GET("items/")
    suspend fun getProductDetail(
        @Query("ids") selectedProductId: String
    ): ApiResponse<List<SearchResultResponseModel>>

    @GET("items/{product_id}/description")
    suspend fun getProductDetailDescription(
        @Path("product_id") productId: String,
    ): ApiResponse<ProductDescriptionModel>
}