package ru.mininn.languagedetection.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class DetectedText() {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var text: String? = null
    var language: String? = null

    constructor(text: String, language: String) : this() {
        this.text = text
        this.language = language
    }
}