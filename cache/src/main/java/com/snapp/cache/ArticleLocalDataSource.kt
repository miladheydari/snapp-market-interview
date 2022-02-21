package com.snapp.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.snapp.cache.dao.ArticlesDao
import com.snapp.cache.mappers.ArticleCacheMapper
import com.snapp.data.models.ArticleEntity
import com.snapp.data.repository.ArticleCache


import javax.inject.Inject

class ArticleLocalDataSource @Inject constructor(
    private val articlesDao: ArticlesDao,
    private val articleCacheMapper: ArticleCacheMapper
) : ArticleCache {
    override fun getArticlesBySourceId(id: String): LiveData<List<ArticleEntity>> {

        return articlesDao.getArticlesBySourceId(id).map {
            it.map(articleCacheMapper::mapFromCached)
        }

    }


    override fun insertArticles(articles: List<ArticleEntity>) {
        articlesDao.insertReplaceArticles(articles.map(articleCacheMapper::mapToCached))

    }
}
