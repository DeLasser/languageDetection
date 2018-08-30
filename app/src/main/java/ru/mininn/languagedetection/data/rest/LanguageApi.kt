package ru.mininn.languagedetection.data.rest

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface LanguageApi {
    @GET("identify")
    fun detectLanguage(@Query("text") text : String) : Observable<ResponseBody>
}