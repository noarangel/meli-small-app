package com.smallmeliapp.home.repository

import android.util.Log
import com.smallmeliapp.utils.SharedPreferencesConstants
import com.smallmeliapp.utils.SharedPreferencesUtil
import javax.inject.Inject

class HomeSharedPreferencesRepository @Inject constructor(private val sharedPreferenceUtil: SharedPreferencesUtil) {

    fun setUserSite(userSiteId: String): Boolean =
        sharedPreferenceUtil.setSharedPreferences(
            SharedPreferencesConstants.ID_USER_SITE,
            userSiteId
        )
            .runCatching { return true }.isSuccess

    fun getUserSite(): String =
        sharedPreferenceUtil.getSharedPreferenceString(
            SharedPreferencesConstants.ID_USER_SITE
        ) ?: ""

}