package com.hemanthddev.rickandmorty.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hemanthddev.rickandmorty.data.db.converter.StringListConverter
import com.hemanthddev.rickandmorty.data.db.dao.EpisodeDao
import com.hemanthddev.rickandmorty.data.db.dao.PageKeyDao
import com.hemanthddev.rickandmorty.data.model.Episode
import com.hemanthddev.rickandmorty.data.model.PageKey

private const val DB_NAME = "rick_and_morty"

@Database(entities = [Episode::class, PageKey::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class AppDB : RoomDatabase() {
    abstract fun episodeDao(): EpisodeDao
    abstract fun pageKeyDao(): PageKeyDao

    companion object {
        @Volatile
        private var instance: AppDB? = null

        fun getDatabase(context: Context): AppDB =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDB::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}