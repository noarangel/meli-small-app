package com.smallmeliapp.core.api.apicalladapter

import com.smallmeliapp.core.api.response.ApiResponse
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ApiResponseCall<T : Any>(private val delegate: Call<T>) : Call<ApiResponse<T>> {

    override fun enqueue(callback: Callback<ApiResponse<T>>) {
        return delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success(ApiResponse.create(response))
                )

            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(
                    this@ApiResponseCall,
                    Response.success(ApiResponse.create<T>(t))
                )
            }

        })

    }

    override fun clone() = ApiResponseCall(delegate.clone())

    override fun execute(): Response<ApiResponse<T>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun isExecuted() = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled() = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout() = delegate.timeout()
}