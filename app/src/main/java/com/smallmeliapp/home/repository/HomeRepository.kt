package com.smallmeliapp.home.repository

import com.smallmeliapp.R
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.smallmeliapp.home.api.HomeDataSource
import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.core.api.response.ApiEmptyResponse
import com.smallmeliapp.core.api.response.ApiErrorResponse
import com.smallmeliapp.core.api.response.ApiSuccessResponse
import com.smallmeliapp.core.extension.StringExtension.toApiException
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeDataSource) {

    @Inject
    lateinit var context: Context

    suspend fun getSites() =
        withContext(Dispatchers.IO) {
            when (val response = api.getSites()) {
                is ApiSuccessResponse -> ApiResult.Success(response.data)
                is ApiEmptyResponse -> ApiResult.Error(
                    context.getString(R.string.default_empty_error).toApiException()
                )
                is ApiErrorResponse -> ApiResult.Error(
                    context.getString(R.string.default_error).toApiException()
                )

            }
        }

    suspend fun getProductsBySearch(siteId: String, findText: String) =
        withContext(Dispatchers.IO) {
            when (val response = api.getProductsBySearch(siteId, findText)) {
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
