package ru.mininn.languagedetection.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import ru.mininn.languagedetection.data.model.DetectedText

@Dao
interface LanguageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetectedText(detectedText: DetectedText)

    @Query("SELECT * FROM DetectedText")
    fun getObservableTextList() : LiveData<DetectedText>

    @Query("SELECT * FROM DetectedText WHERE text = :text")
    fun getDetectedText(text: String) : DetectedText

}