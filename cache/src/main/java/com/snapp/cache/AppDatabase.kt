package com.snapp.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.snapp.cache.dao.ArticlesDao
import com.snapp.cache.dao.SourceDao
import com.snapp.cache.models.ArticleCacheEntity
import com.snapp.cache.models.SourceCacheEntity


@Database(
    entities = [ArticleCacheEntity::class, SourceCacheEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao
    abstract fun sourceDao(): SourceDao


    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }

        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "SnappMarket.db"
            )

                .build()

    }
}