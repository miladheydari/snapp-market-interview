package com.miladheydari.snappmarketandroidtest.domain.datasource

import com.miladheydari.snappmarketandroidtest.db.dao.ArticlesDao


import com.miladheydari.snappmarketandroidtest.domain.model.Article

import javax.inject.Inject

class ArticlesLocalDataSource @Inject constructor(private val articlesDao: ArticlesDao) {


    fun getArticlesBySourceId(id: String) = articlesDao.getArticlesBySourceId(id)

    fun insertArticles(articles: List<Article>) = articlesDao.insertReplaceArticles(articles)
}
