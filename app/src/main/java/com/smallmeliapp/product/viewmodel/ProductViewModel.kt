package com.smallmeliapp.product.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smallmeliapp.core.api.exception.ApiException
import com.smallmeliapp.model.ProductDetailModelUI
import com.smallmeliapp.product.usecase.ProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productDetailUseCase: ProductDetailUseCase,
) : ViewModel() {
    val productDetailModelUI = MutableLiveData<ProductDetailModelUI>()
    val productDetailError = MutableLiveData<ApiException>()
    val productDescriptionError = MutableLiveData<ApiException>()
    val isLoading = MutableLiveData<Boolean>(false)

    fun getDetailProduct(productId: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            productDetailUseCase(productId).runCatching {
                productDetailUseCase.emitProductDetailModelUI().collect {
                    productDetailModelUI.postValue(it)
                }
                productDetailUseCase.emitProductDetailError().collect {
                    productDetailError.postValue(it)
                }
                productDetailUseCase.emitProductDescriptionError().collect {
                    productDescriptionError.postValue(it)
                }
            }
            isLoading.postValue(false)
        }
    }

}