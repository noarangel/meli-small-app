package com.smallmeliapp.home.usecase

import android.content.Context
import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.core.api.exception.ApiException
import com.smallmeliapp.home.repository.HomeRepository
import com.smallmeliapp.home.repository.HomeSharedPreferencesRepository
import com.smallmeliapp.model.SearchResultsModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class HomeSearchUseCaseTest {

    @RelaxedMockK
    private lateinit var homeRepository: HomeRepository

    @RelaxedMockK
    private lateinit var homeSharedPreferencesRepository: HomeSharedPreferencesRepository

    lateinit var homeSearchUseCase: HomeSearchUseCase
    lateinit var searchResultMockK: SearchResultsModel
    lateinit var mockkContext: Context
    lateinit var successResponse: ApiResult.Success<SearchResultsModel>
    lateinit var failureResponse: ApiResult.Error
    lateinit var findText: String
    lateinit var userSite: String

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        homeSearchUseCase = HomeSearchUseCase(homeRepository, homeSharedPreferencesRepository)
        mockkContext = mockk<Context>(relaxed = true)
        homeSearchUseCase.context = mockkContext
        searchResultMockK = mockk<SearchResultsModel>(relaxed = true)
        successResponse = ApiResult.Success(searchResultMockK)
        failureResponse = ApiResult.Error(ApiException("Error"))
        findText = "some text"
        userSite = "MCO"
    }

    @Test
    fun `should the shared preferences return empty user site`() = runBlocking {
        //Given
        coEvery { homeSharedPreferencesRepository.getUserSite() } returns ""
        //When
        homeSearchUseCase(findText)
        //Then
        coVerify(exactly = 1) { homeSearchUseCase(findText) is ApiResult.Error }
    }

    @Test
    fun `should the shared preferences return user site`() = runBlocking {
        //Given
        coEvery { homeSharedPreferencesRepository.getUserSite() } returns userSite
        coEvery {
            homeRepository.getProductsBySearch(
                userSite,
                findText
            )
        } returns successResponse

        homeSearchUseCase(findText)
        //Then
        coVerify(exactly = 1) {
            homeRepository.getProductsBySearch(userSite, findText)
        }
    }

    @Test
    fun `should the home repository return success`() = runBlocking {
        //Given
        coEvery { homeSharedPreferencesRepository.getUserSite() } returns userSite
        coEvery {
            homeRepository.getProductsBySearch(
                userSite,
                findText
            )
        } returns successResponse

        //When
        val response = homeSearchUseCase(findText)
        //Then
        coVerify(exactly = 1) {
            homeRepository.getProductsBySearch(userSite, findText)
        }
        assert(response == successResponse)
    }

    @Test
    fun `should the home repository return error`() = runBlocking {
        //Given
        coEvery { homeSharedPreferencesRepository.getUserSite() } returns userSite
        coEvery {
            homeRepository.getProductsBySearch(
                userSite,
                findText
            )
        } returns failureResponse

        //When
        val response = homeSearchUseCase(findText)
        //Then
        coVerify(exactly = 1) {
            homeRepository.getProductsBySearch(userSite, findText)
        }
        assert(response == failureResponse)
    }
}