package ru.mininn.languagedetection.ui.detection

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.mininn.languagedetection.data.LanguageRepository
import ru.mininn.languagedetection.data.model.DetectionHolder

class DetectViewModel(application: Application) : AndroidViewModel(application) {
    val languageRepository = LanguageRepository(application.applicationContext)

    var  detectedTextLiveData = MutableLiveData<DetectionHolder>()
    var disposable : Disposable? = null


    fun detectLanguage(text: String) {
        disposable?.dispose()
        disposable = languageRepository.getDetectedText(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    detectedTextLiveData.value = DetectionHolder(it, null)
                }, {
                    detectedTextLiveData.value = DetectionHolder(null, it)
                })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}