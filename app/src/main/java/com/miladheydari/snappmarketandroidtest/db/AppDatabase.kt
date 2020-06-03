package com.miladheydari.snappmarketandroidtest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.miladheydari.snappmarketandroidtest.db.dao.ArticlesDao
import com.miladheydari.snappmarketandroidtest.db.dao.SourcesDao
import com.miladheydari.snappmarketandroidtest.domain.model.Article
import com.miladheydari.snappmarketandroidtest.domain.model.Source


@Database(
    entities = [Article::class, Source::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articlesDao(): ArticlesDao
    abstract fun sourcesDao(): SourcesDao


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