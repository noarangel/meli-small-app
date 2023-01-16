package com.smallmeliapp.home.usecase

import com.smallmeliapp.home.repository.HomeSharedPreferencesRepository
import javax.inject.Inject

class HomeSiteUseCase @Inject constructor(private val homeSharedPreferencesRepository: HomeSharedPreferencesRepository) {

    operator fun invoke(userSiteId: String): Boolean =
        homeSharedPreferencesRepository.setUserSite(userSiteId)

}