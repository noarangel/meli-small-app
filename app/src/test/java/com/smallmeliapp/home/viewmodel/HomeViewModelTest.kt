package com.smallmeliapp.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.core.api.exception.ApiException
import com.smallmeliapp.home.usecase.HomeSearchUseCase
import com.smallmeliapp.home.usecase.HomeSiteUseCase
import com.smallmeliapp.home.usecase.HomeUseCase
import com.smallmeliapp.model.SearchResultsModel
import com.smallmeliapp.model.SiteModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
internal class HomeViewModelTest {
    @RelaxedMockK
    lateinit var homeUseCase: HomeUseCase

    @RelaxedMockK
    lateinit var homeSiteUseCase: HomeSiteUseCase

    @RelaxedMockK
    lateinit var homeSearchUseCase: HomeSearchUseCase

    lateinit var homeViewModel: HomeViewModel
    lateinit var searchResultMockK: SearchResultsModel
    lateinit var successSitesListResponse: ApiResult.Success<List<SiteModel>>
    lateinit var successQueryResponse: ApiResult.Success<SearchResultsModel>
    var successSetUseSiteResponse: Boolean = false
    lateinit var errorSitesListResponse: ApiResult.Error
    lateinit var siteMockk: SiteModel
    lateinit var findText: String
    lateinit var userSite: String

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(homeUseCase, homeSiteUseCase, homeSearchUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
        searchResultMockK = mockk<SearchResultsModel>(relaxed = true)
        successSitesListResponse = ApiResult.Success(listOf())
        successQueryResponse = ApiResult.Success(searchResultMockK)
        successSetUseSiteResponse = false
        errorSitesListResponse = ApiResult.Error(ApiException("Error"))
        siteMockk = mockk<SiteModel>(relaxed = true)
        findText = "some text"
        userSite = "MCO"
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should init view model return post api result values`() {
        //Given
        coEvery { homeUseCase() } returns successSitesListResponse
        //When
        homeViewModel.init()
        //Then
        assert(homeViewModel.sites.value == successSitesListResponse.data)
        coVerifyOrder {
            homeViewModel.isLoading.postValue(true)
            homeUseCase()
            homeViewModel.isLoading.postValue(false)
        }

    }

    @Test
    fun `should init view model return post api result error`() {
        //Given
        coEvery { homeUseCase() } returns errorSitesListResponse
        //When
        homeViewModel.init()
        //Then
        assert(homeViewModel.errorObserver.value == errorSitesListResponse.exception)
        coVerifyOrder {
            homeViewModel.isLoading.postValue(true)
            homeUseCase()
            homeViewModel.isLoading.postValue(false)
        }

    }

    @Test
    fun `should set user site return post api result values`() {
        //Given
        coEvery { homeSiteUseCase(userSite) } returns successSetUseSiteResponse
        //When
        homeViewModel.setUserSite(userSite)
        //Then
        coVerifyOrder {
            homeViewModel.isLoading.postValue(true)
            homeSiteUseCase(userSite)
            homeViewModel.isLoading.postValue(false)
        }

    }

    @Test
    fun `should on site list item click call set user site`() {
        //Given
        coEvery { homeSiteUseCase(userSite) } returns successSetUseSiteResponse
        //When
        homeViewModel.onSiteListItemClick(siteMockk)
        //Then
        coVerify { homeViewModel.setUserSite(siteMockk.id) }

    }

    @Test
    fun `should search return success return post api result values`() {
        //Given
        coEvery { homeSearchUseCase(findText) } returns successQueryResponse
        //When
        homeViewModel.getSearchResults(findText)
        //Then
        assert(homeViewModel.searchResult.value == successQueryResponse.data)
        coVerifyOrder {
            homeViewModel.isLoading.postValue(true)
            homeSearchUseCase(findText)
            homeViewModel.isLoading.postValue(false)
        }

    }

    @Test
    fun `should search return error return post api result values`() {
        //Given
        coEvery { homeSearchUseCase(findText) } returns errorSitesListResponse
        //When
        homeViewModel.getSearchResults(findText)
        //Then
        assert(homeViewModel.errorObserver.value == errorSitesListResponse.exception)
        coVerifyOrder {
            homeViewModel.isLoading.postValue(true)
            homeSearchUseCase(findText)
            homeViewModel.isLoading.postValue(false)
        }
    }
}