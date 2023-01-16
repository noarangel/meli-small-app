package co.picap.passenger.newarchitecture.core.api

import android.content.Context
import android.net.Uri
import com.smallmeliapp.BuildConfig
import com.smallmeliapp.R
import com.smallmeliapp.core.api.annotation.TagServiceApis
import com.smallmeliapp.core.api.either.ApiResult
import com.smallmeliapp.core.api.exception.NoConnectivityException
import com.smallmeliapp.core.extension.StringExtension.toApiException
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RetrofitInterceptor : Interceptor {

    @Inject
    lateinit var context: Context


    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = chain.request().url

        val newRequest = request.newBuilder()

        if (!BuildConfig.DEBUG) {
            originalRequest.tag(Invocation::class.java)?.let { invocation ->
                invocation.method().getAnnotation(TagServiceApis::class.java)?.let { tag ->
                    val host = getHostFromBaseUrl()
                    host?.let {
                        newRequest.host(it)
                        chain.withConnectTimeout(3, TimeUnit.MINUTES)
                        chain.withReadTimeout(3, TimeUnit.MINUTES)
                        chain.withWriteTimeout(3, TimeUnit.MINUTES)
                    }
                }
            }
        }

        return kotlin.runCatching {
            chain.proceed(
                originalRequest
                    .newBuilder()
                    .url(
                        newRequest.build()
                    )
                    .build()
            )
        }.onFailure {
            throw NoConnectivityException(context.getString(R.string.default_error))
        }.getOrThrow()
    }

    private fun getHostFromBaseUrl(): String? {
        val uri = Uri.parse(BuildConfig.BASE_URL)
        return uri.host
    }
}