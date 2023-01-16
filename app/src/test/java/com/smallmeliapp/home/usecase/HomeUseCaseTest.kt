package com.smallmeliapp.home.usecase

import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.core.api.exception.ApiException
import com.smallmeliapp.home.repository.HomeRepository
import com.smallmeliapp.model.SiteModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class HomeUseCaseTest {

    @RelaxedMockK
    private lateinit var homeRepository: HomeRepository

    lateinit var homeUseCase: HomeUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        homeUseCase = HomeUseCase(homeRepository)
    }

    @Test
    fun `should return success when invoke use case`() = runBlocking {
        //Given
        val siteModel = mockk<SiteModel>(relaxed = true)
        val apiSuccessResponse = ApiResult.Success(listOf(siteModel))
        coEvery { homeRepository.getSites() } returns apiSuccessResponse
        //When
        val result = homeUseCase()
        //Then
        assert(result is ApiResult.Success)
        assert(result == apiSuccessResponse)
    }

    @Test
    fun `should return error when invoke use case`() = runBlocking {
        //Given
        val apiErrorResponse = ApiResult.Error(ApiException("Error"))
        coEvery { homeRepository.getSites() } returns apiErrorResponse
        //When
        val result = homeUseCase()
        //Then
        assert(result is ApiResult.Error)
        assert(result == apiErrorResponse)
    }
}