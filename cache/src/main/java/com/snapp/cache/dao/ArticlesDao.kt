package com.snapp.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.snapp.cache.models.ArticleCacheEntity


@Dao
interface ArticlesDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplaceArticles(articles: List<ArticleCacheEntity>)

    @Query("SELECT * FROM article")
    fun getArticles(): LiveData<List<ArticleCacheEntity>>


    @Query("SELECT * FROM article where id=:id")
    fun getArticlesBySourceId(id:String): LiveData<List<ArticleCacheEntity>>

    @Transaction
    fun deleteAndInsertArticles(articles: List<ArticleCacheEntity>) {
        deleteArticles()
        insertReplaceArticles(articles)
    }

    @Query("DELETE FROM article")
    fun deleteArticles()

}