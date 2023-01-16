package com.smallmeliapp.product.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.core.api.exception.ApiException
import com.smallmeliapp.model.ProductDescriptionModel
import com.smallmeliapp.model.ProductDetailModelUI
import com.smallmeliapp.model.ProductItemDetailModel
import com.smallmeliapp.model.SearchResultResponseModel
import com.smallmeliapp.product.repository.ProductRepository
import com.smallmeliapp.product.usecase.ProductDetailUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class ProductViewModelTest {
    @RelaxedMockK
    private lateinit var producDetailRepository: ProductRepository
    lateinit var productDetailUseCase: ProductDetailUseCase
    lateinit var productViewModel: ProductViewModel

    lateinit var searchResultResponseList: List<SearchResultResponseModel>
    lateinit var successDetailResponse: ApiResult.Success<List<SearchResultResponseModel>>
    lateinit var successDescriptionResponse: ApiResult.Success<ProductDescriptionModel>
    lateinit var productItemDetailModel: ProductItemDetailModel
    lateinit var productDescriptionModel: ProductDescriptionModel
    lateinit var productDetailModelUI: ProductDetailModelUI
    lateinit var failureProductDetailUseCase: ApiException
    lateinit var productId: String

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        productDetailUseCase = ProductDetailUseCase(producDetailRepository)
        productViewModel = ProductViewModel(productDetailUseCase)
        searchResultResponseList =
            List<SearchResultResponseModel>(size = 1) { mockk<SearchResultResponseModel>(relaxed = true) }
        successDetailResponse = ApiResult.Success(searchResultResponseList)
        successDescriptionResponse =
            ApiResult.Success<ProductDescriptionModel>(mockk(relaxed = true))
        productItemDetailModel = mockk(relaxed = true)
        productDescriptionModel = mockk(relaxed = true)
        productDetailModelUI = ProductDetailModelUI(productItemDetailModel, productDescriptionModel)
        failureProductDetailUseCase = ApiException("Error")
        productId = "product_id"
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get detail product should repository return a productDetailModelUI`(): Unit = runBlocking {
        // Given
        coEvery { producDetailRepository.getProductDetail(any()) } returns successDetailResponse
        coEvery { producDetailRepository.getProductDetailDescription(any()) } returns successDescriptionResponse
        // When
        productViewModel.getDetailProduct(productId)
        // Then

        productViewModel.viewModelScope.launch {
            coVerify(exactly = 1) { producDetailRepository.getProductDetail(productId) }
            coVerify(exactly = 1) { producDetailRepository.getProductDetailDescription(productId) }
            coVerify { productDetailUseCase(productId) }
            coVerify { productDetailUseCase.emitProductDescriptionError() }
            coVerify { productDetailUseCase.emitProductDetailError() }
            coVerify { productDetailUseCase.emitProductDetailModelUI() }
            assert(productViewModel.productDetailModelUI.value == productDetailUseCase.getProductDetailModelUI())
        }
    }

    @Test
    fun `get detail product should repository return a productDetailModelUI with error`(): Unit =
        runBlocking {
            // Given
            coEvery { producDetailRepository.getProductDetail(any()) } returns ApiResult.Error(
                failureProductDetailUseCase
            )
            coEvery { producDetailRepository.getProductDetailDescription(any()) } returns ApiResult.Error(
                failureProductDetailUseCase
            )
            // When
            productViewModel.getDetailProduct(productId)
            // Then

            productViewModel.viewModelScope.launch {
                coVerify(exactly = 1) { producDetailRepository.getProductDetail(productId) }
                coVerify(exactly = 1) { producDetailRepository.getProductDetailDescription(productId) }
                coVerify { productDetailUseCase(productId) }
                coVerify { productDetailUseCase.emitProductDescriptionError() }
                coVerify { productDetailUseCase.emitProductDetailError() }
                coVerify { productDetailUseCase.emitProductDetailModelUI() }
                assert(productViewModel.productDetailError.value == productDetailUseCase.getProductDetailError())
                assert(productViewModel.productDescriptionError.value == productDetailUseCase.getProductDescriptionError())
            }
        }

}