package com.miladheydari.snappmarketandroidtest.repo

import NetworkBoundResource
import androidx.lifecycle.LiveData
import com.miladheydari.snappmarketandroidtest.core.Constants.NetworkService.RATE_LIMITER_TYPE
import com.miladheydari.snappmarketandroidtest.domain.datasource.ArticlesLocalDataSource
import com.miladheydari.snappmarketandroidtest.domain.datasource.ArticlesRemoteDataSource

import com.miladheydari.snappmarketandroidtest.domain.model.Article
import com.miladheydari.snappmarketandroidtest.domain.model.ArticleList

import com.miladheydari.snappmarketandroidtest.utils.domain.RateLimiter
import com.miladheydari.snappmarketandroidtest.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val articleRemoteDataSource: ArticlesRemoteDataSource,
    private val articleLocalDataSource: ArticlesLocalDataSource
) {

    private val rateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadArticleBySourceId(
        sourceId: String,
        page: Int,
        pageSize: Int,
        fetchRequired: Boolean
    ): LiveData<Resource<List<Article>>> {
        return object :
            NetworkBoundResource<List<Article>, ArticleList>() {
            override fun saveCallResult(item: ArticleList) {

                articleLocalDataSource.insertArticles(item.articles)
            }

            override fun shouldFetch(data: List<Article>?): Boolean {
                return (fetchRequired && (data.isNullOrEmpty() || (data.isNotEmpty() && page > 0)))
            }

            override fun loadFromDb(): LiveData<List<Article>> {
                return articleLocalDataSource.getArticlesBySourceId(sourceId)
            }

            override fun createCall(): Single<ArticleList> {
                return articleRemoteDataSource.getArticles(
                    sourceId, page, pageSize
                )
            }

            override fun onFetchFailed() {
                rateLimit.reset(RATE_LIMITER_TYPE)
            }

        }.asLiveData
    }
}
