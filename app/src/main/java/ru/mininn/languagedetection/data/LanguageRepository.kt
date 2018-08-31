package ru.mininn.languagedetection.data


import android.content.Context
import io.reactivex.Observable
import ru.mininn.languagedetection.data.database.LanguageDatabase
import ru.mininn.languagedetection.data.model.DetectedText
import ru.mininn.languagedetection.data.rest.LanguageApi
import ru.mininn.languagedetection.data.rest.LanguageApiClient

class LanguageRepository {
    constructor(context: Context) {
        this.languageDatabase = LanguageDatabase.databaseBuilder(context).build()
        this.languageApi = LanguageApiClient.create(context)
    }

    val languageDatabase: LanguageDatabase
    val languageApi: LanguageApi

    fun getDetectedText(text: String): Observable<DetectedText> {
        return languageApi.detectLanguage(text)
                .map {
                    return@map DetectedText(text, it.string())
                }.doOnNext {
                    languageDatabase.getLanguageDao().insertDetectedText(it)
                }
    }

}