package com.smallmeliapp.home.usecase

import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.home.repository.HomeRepository
import com.smallmeliapp.model.SiteModel
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    suspend operator fun invoke(): ApiResult<List<SiteModel>> {
        return when (val response = homeRepository.getSites()) {
            is ApiResult.Error -> ApiResult.Error(response.exception)
            is ApiResult.Success -> ApiResult.Success(response.data)
        }
    }
}