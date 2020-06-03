package com.miladheydari.snappmarketandroidtest.di.module

import android.content.Context
import com.miladheydari.snappmarketandroidtest.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): AppDatabase{
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun providesSourcesDao(db: AppDatabase) = db.sourcesDao()

    @Provides
    @Singleton
    fun providesArticleDao(db:AppDatabase) = db.articlesDao()
}
