package com.smallmeliapp.core.api.apicalladapter

import com.smallmeliapp.core.api.exception.NoConnectivityException
import com.smallmeliapp.core.api.response.ApiResponse
import dagger.hilt.InstallIn
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class CoroutineCallAdapterFactory private constructor() : CallAdapter.Factory() {

    companion object {
        @JvmStatic
        @JvmName("create")
        operator fun invoke() = CoroutineCallAdapterFactory()
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        check(returnType is ParameterizedType) { "Return type must be parameterized as Deferred<Foo> or Deferred<out Foo>" }
        val responseType = getParameterUpperBound(0, returnType)

        val rawDeferredType = getRawType(responseType)
        if (rawDeferredType != ApiResponse::class.java) {
            return null
        }

        check(responseType is ParameterizedType) { "Response must be parameterized as NetworkResponse<Foo> or NetworkResponse<out Foo>" }

        val successBodyType = getParameterUpperBound(0, responseType)

        return ResponseCallAdapter<Any>(successBodyType)

    }

    private class ResponseCallAdapter<T : Any>(private val responseType: Type) :
        CallAdapter<T, Call<ApiResponse<T>>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>): Call<ApiResponse<T>> {
            return ApiResponseCall(call)
        }
    }

}
