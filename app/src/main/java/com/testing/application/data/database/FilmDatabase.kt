package com.testing.application.data.database

import android.content.Context
import androidx.room.*

@Database(entities = [FilmEntity::class, Genre::class], version = 1, exportSchema = false)
@TypeConverters(value = [Converter::class])
abstract class FilmDatabase: RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun genreDao(): GenreDao

    companion object {
        fun buildDatabase(context: Context): FilmDatabase {
            return Room.databaseBuilder(context, FilmDatabase::class.java, "Films").build()
        }
    }
}