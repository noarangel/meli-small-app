package com.smallmeliapp.product.usecase

import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.core.api.exception.ApiException
import com.smallmeliapp.model.ProductDescriptionModel
import com.smallmeliapp.model.ProductDetailModelUI
import com.smallmeliapp.model.ProductItemDetailModel
import com.smallmeliapp.product.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductDetailUseCase @Inject constructor(private val productRepository: ProductRepository) {

    private var productDetailModelUI: ProductDetailModelUI =
        ProductDetailModelUI(null, null)
    private lateinit var productDescriptionError: ApiException
    private lateinit var productDetailError: ApiException

    suspend operator fun invoke(productId: String) {
        when (val productDetail = productRepository.getProductDetail(productId)) {
            is ApiResult.Error -> setProductDetailError(productDetail.exception)
            is ApiResult.Success -> setProductDetail(
                productDetail.data.first().body
            )
        }
        when (val productDescription = productRepository.getProductDetailDescription(productId)) {
            is ApiResult.Error -> setProductDescriptionError(productDescription.exception)
            is ApiResult.Success -> setProductDescription(productDescription.data)
        }
    }

    fun setProductDetail(productItemDetailModel: ProductItemDetailModel) {
        this.productDetailModelUI.productItemDetailModel = productItemDetailModel
    }

    fun setProductDescription(productDescriptionModel: ProductDescriptionModel) {
        this.productDetailModelUI.descriptionModel = productDescriptionModel
    }

    fun setProductDetailError(productDetailError: ApiException) {
        this.productDetailError = productDetailError
    }

    fun setProductDescriptionError(productDescriptionError: ApiException) {
        this.productDescriptionError = productDescriptionError
    }

    fun getProductDetailModelUI() = productDetailModelUI

    fun getProductDetailError() = productDetailError

    fun getProductDescriptionError() = productDescriptionError

    fun emitProductDetailModelUI(): Flow<ProductDetailModelUI> =
        flow {
            emit(productDetailModelUI)
        }

    fun emitProductDetailError(): Flow<ApiException> = flow {
        emit(productDetailError)
    }

    fun emitProductDescriptionError(): Flow<ApiException> = flow {
        emit(productDescriptionError)
    }
}
