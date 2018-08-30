package ru.mininn.languagedetection.data.database

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import ru.mininn.languagedetection.data.model.DetectedText

@android.arch.persistence.room.Database(entities = [DetectedText::class], version = 1)
abstract class LanguageDatabase : RoomDatabase() {
    companion object {
        fun databaseBuilder(context: Context): Builder<LanguageDatabase> {
            return Room.databaseBuilder(context, LanguageDatabase::class.java, "database")
        }
    }

    abstract fun getLanguageDao() : LanguageDao
}