package com.miladheydari.snappmarketandroidtest.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.miladheydari.snappmarketandroidtest.domain.model.Article


@Dao
interface ArticlesDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplaceArticles(articles: List<Article>)

    @Query("SELECT * FROM article")
    fun getArticles(): LiveData<List<Article>>


    @Query("SELECT * FROM article where id=:id")
    fun getArticlesBySourceId(id:String): LiveData<List<Article>>

    @Transaction
    fun deleteAndInsertArticles(articles: List<Article>) {
        deleteArticles()
        insertReplaceArticles(articles)
    }

    @Query("DELETE FROM article")
    fun deleteArticles()

}