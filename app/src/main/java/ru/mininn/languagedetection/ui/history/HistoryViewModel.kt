package ru.mininn.languagedetection.ui.history

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import ru.mininn.languagedetection.data.database.LanguageDatabase
import ru.mininn.languagedetection.data.model.DetectedText

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    var database: LanguageDatabase = LanguageDatabase.databaseBuilder(application.applicationContext).build()
    val historyLiveData: LiveData<List<DetectedText>> by lazy { database.getLanguageDao().getObservableTextList() }

    override fun onCleared() {
        super.onCleared()
        database.close()
    }
}