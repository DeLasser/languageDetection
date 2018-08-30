package ru.mininn.languagedetection.data


import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
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
    var behaviorSubject: BehaviorSubject<DetectedText>? = null

    fun getDetectedText(text: String): Observable<DetectedText> {
        behaviorSubject = BehaviorSubject.create()
        return languageApi.detectLanguage(text)
                .map {
                    return@map DetectedText(text, it.string())
                }
                .doOnNext {
                    languageDatabase.getLanguageDao().insertDetectedText(it)
                    languageDatabase.close()
                }
    }

    fun getDetectedTextHistory(): LiveData<DetectedText> {
        return languageDatabase.getLanguageDao().getObservableTextList()
    }
}