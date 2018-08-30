package ru.mininn.languagedetection.data.rest

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
                .header("Authorization", apiKey).build()
        Log.d("asdasd", request.url().toString())
        return chain.proceed(authenticatedRequest)
    }
}