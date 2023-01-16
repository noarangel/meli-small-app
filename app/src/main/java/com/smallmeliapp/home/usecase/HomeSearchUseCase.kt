package com.smallmeliapp.home.usecase

import com.smallmeliapp.R
import android.content.Context
import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.core.extension.StringExtension.toApiException
import com.smallmeliapp.home.repository.HomeRepository
import com.smallmeliapp.home.repository.HomeSharedPreferencesRepository
import com.smallmeliapp.model.SearchResultsModel
import javax.inject.Inject

class HomeSearchUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val homeSharedPreferencesRepository: HomeSharedPreferencesRepository
) {

    @Inject
    lateinit var context: Context

    suspend operator fun invoke(findText: String): ApiResult<SearchResultsModel> {
        homeSharedPreferencesRepository.getUserSite().let { userSite ->
            if (userSite.isEmpty()) {
                return ApiResult.Error(context.getString(R.string.default_error).toApiException())
            }
            return when (val response = homeRepository.getProductsBySearch(userSite, findText)) {
                is ApiResult.Success -> ApiResult.Success(response.data)
                is ApiResult.Error -> ApiResult.Error(response.exception)
            }
        }
    }
}