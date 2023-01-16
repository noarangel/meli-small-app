package com.smallmeliapp.product.repository

import android.content.Context
import com.smallmeliapp.R
import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.core.api.response.ApiEmptyResponse
import com.smallmeliapp.core.api.response.ApiErrorResponse
import com.smallmeliapp.core.api.response.ApiSuccessResponse
import com.smallmeliapp.core.extension.StringExtension.toApiException
import com.smallmeliapp.product.api.ProductDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepository @Inject constructor(private val api: ProductDataSource) {

    @Inject
    lateinit var context: Context

    suspend fun getProductDetail(productId: String) =
        withContext(Dispatchers.IO) {
            when (val response = api.getProductDetail(productId)) {
                is ApiSuccessResponse -> ApiResult.Success(response.data)
                is ApiEmptyResponse -> ApiResult.Error(
                    context.getString(R.string.default_empty_error).toApiException()
                )
                is ApiErrorResponse -> ApiResult.Error(
                    context.getString(R.string.default_error).toApiException()
                )
            }
        }

    suspend fun getProductDetailDescription(productId: String) =
        withContext(Dispatchers.IO) {
            when (val response = api.getProductDetailDescription(productId)) {
                is ApiSuccessResponse -> ApiResult.Success(response.data)
                is ApiEmptyResponse -> ApiResult.Error(
                    context.getString(R.string.default_empty_error).toApiException()
                )
                is ApiErrorResponse -> ApiResult.Error(
                    context.getString(R.string.default_error).toApiException()
                )
            }
        }
}