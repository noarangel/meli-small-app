package com.smallmeliapp.home.usecase

import com.smallmeliapp.home.repository.HomeSharedPreferencesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import org.junit.Before
import org.junit.Test


internal class HomeSiteUseCaseTest {
    @RelaxedMockK
    private lateinit var homeSharedPreferencesRepository: HomeSharedPreferencesRepository

    private lateinit var homeSiteUseCase: HomeSiteUseCase
    private lateinit var userSiteId: String


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        homeSharedPreferencesRepository = mockk()
        homeSiteUseCase = HomeSiteUseCase(homeSharedPreferencesRepository)
        userSiteId = "MCO"
    }

    @Test
    fun `should return true when set user site`() {
        //Given
        coEvery { homeSharedPreferencesRepository.setUserSite(userSiteId) } returns true
        //When
        val response = homeSiteUseCase(userSiteId)
        //Then
        assert(response)

    }

    @Test
    fun `should return false when set user site`() {
        //Given
        coEvery { homeSharedPreferencesRepository.setUserSite(userSiteId) } returns false
        //When
        val response = homeSiteUseCase(userSiteId)
        //Then
        assert(!response)
    }
}