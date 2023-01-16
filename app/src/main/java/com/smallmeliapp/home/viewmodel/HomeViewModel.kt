package com.smallmeliapp.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.smallmeliapp.R
import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.core.api.exception.ApiException
import com.smallmeliapp.home.usecase.HomeSearchUseCase
import com.smallmeliapp.home.usecase.HomeSiteUseCase
import com.smallmeliapp.home.usecase.HomeUseCase
import com.smallmeliapp.model.SearchResultsModel
import com.smallmeliapp.model.SiteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase,
    private val homeSiteUseCase: HomeSiteUseCase,
    private val homeSearchUseCase: HomeSearchUseCase
) : ViewModel() {

    val sites = MutableLiveData<List<SiteModel>>()
    val searchResult = MutableLiveData<SearchResultsModel?>()
    val errorObserver = MutableLiveData<ApiException>()
    val isLoading = MutableLiveData<Boolean>(false)

    fun init() {
        viewModelScope.launch {
            isLoading.postValue(true)
            when (val response = homeUseCase()) {
                is ApiResult.Error -> errorObserver.postValue(response.exception)
                is ApiResult.Success -> sites.postValue(
                    response.data
                )
            }
            isLoading.postValue(false)
        }
    }

    fun setUserSite(userSiteId: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            homeSiteUseCase(userSiteId).runCatching {
                isLoading.postValue(false)
            }.onFailure {
                //Dialog
            }
        }
    }

    fun onSiteListItemClick(site: SiteModel) = setUserSite(site.id)

    fun getSearchResults(findText: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = homeSearchUseCase(findText)
            when (response) {
                is ApiResult.Error -> errorObserver.postValue(response.exception)
                is ApiResult.Success -> searchResult.postValue(response.data)
            }
            isLoading.postValue(false)
        }
    }
}