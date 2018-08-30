package ru.mininn.languagedetection.data.rest

import android.content.Context
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.mininn.languagedetection.Constants
import ru.mininn.languagedetection.R


class LanguageApiClient {
    companion object {
        fun create(context: Context): LanguageApi {
            val client = OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor(Credentials
                            .basic(context.getString(R.string.api_username),context.getString(R.string.api_password))))
                    .build()
            return Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(LanguageApi::class.java)
        }
    }
}