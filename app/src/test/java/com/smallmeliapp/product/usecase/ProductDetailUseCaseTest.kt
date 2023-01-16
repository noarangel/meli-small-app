package com.smallmeliapp.product.usecase

import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.core.api.exception.ApiException
import com.smallmeliapp.model.ProductDescriptionModel
import com.smallmeliapp.model.SearchResultResponseModel
import com.smallmeliapp.product.repository.ProductRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class ProductDetailUseCaseTest {

    @RelaxedMockK
    private lateinit var producDetailRepository: ProductRepository

    lateinit var productDetailUseCase: ProductDetailUseCase
    lateinit var searchResultResponseList: List<SearchResultResponseModel>
    lateinit var successDetailResponse: ApiResult.Success<List<SearchResultResponseModel>>
    lateinit var successDescriptionResponse: ApiResult.Success<ProductDescriptionModel>
    lateinit var failureDetailResponse: ApiResult.Error
    lateinit var failureDescriptionResponse: ApiResult.Error
    lateinit var productId: String

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        productDetailUseCase = ProductDetailUseCase(producDetailRepository)
        searchResultResponseList =
            List<SearchResultResponseModel>(size = 1) { mockk<SearchResultResponseModel>(relaxed = true) }
        successDetailResponse = ApiResult.Success(searchResultResponseList)
        successDescriptionResponse =
            ApiResult.Success<ProductDescriptionModel>(mockk(relaxed = true))
        failureDetailResponse = ApiResult.Error(ApiException("Error"))
        failureDescriptionResponse = ApiResult.Error(ApiException("Error"))
        productId = "product_id"
    }

    @Test
    fun `should get detail return success`() = runBlocking {
        //Given
        coEvery { producDetailRepository.getProductDetail(any()) } returns successDetailResponse
        //When
        productDetailUseCase(productId)
        //Then
        coVerify(exactly = 1) { producDetailRepository.getProductDetail(any()) }
        productDetailUseCase.emitProductDetailModelUI().collect {
            coVerify(exactly = 1) { producDetailRepository.getProductDetail(any()) is ApiResult.Success }
            assert(it.productItemDetailModel == successDetailResponse.data.first().body)
        }
    }

    @Test
    fun `should get product detail return error`() = runBlocking {
        //Given
        coEvery { producDetailRepository.getProductDetail(any()) } returns failureDetailResponse
        //When
        productDetailUseCase(productId)
        //Then
        coVerify(exactly = 1) { producDetailRepository.getProductDetail(any()) }
        productDetailUseCase.emitProductDetailError().collect {
            coVerify(exactly = 1) { producDetailRepository.getProductDetail(any()) is ApiResult.Error }
            assert(it == failureDetailResponse.exception)
        }
    }

    @Test
    fun `should get product description return success`() = runBlocking {
        //Given
        coEvery { producDetailRepository.getProductDetailDescription(any()) } returns successDescriptionResponse
        //When
        productDetailUseCase(productId)
        //Then
        coVerify(exactly = 1) { producDetailRepository.getProductDetailDescription(any()) }
        productDetailUseCase.emitProductDetailModelUI().collect {
            coVerify(exactly = 1) { producDetailRepository.getProductDetailDescription(any()) is ApiResult.Success }
            assert(it.descriptionModel == successDescriptionResponse.data)
        }
    }

    @Test
    fun `should get product description return error`() = runBlocking {
        //Given
        coEvery { producDetailRepository.getProductDetailDescription(any()) } returns failureDescriptionResponse
        //When
        productDetailUseCase(productId)
        //Then
        coVerify(exactly = 1) { producDetailRepository.getProductDetailDescription(any()) }
        productDetailUseCase.emitProductDescriptionError().collect {
            coVerify(exactly = 1) { producDetailRepository.getProductDetailDescription(any()) is ApiResult.Error }
            assert(it == failureDescriptionResponse.exception)
        }
    }
}