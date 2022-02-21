package com.snapp.data.repository

import androidx.lifecycle.LiveData
import com.snapp.data.models.ArticleEntity


interface ArticleCache {

    fun getArticlesBySourceId(id: String): LiveData<List<ArticleEntity>>
    fun insertArticles(articles: List<ArticleEntity>)

}
