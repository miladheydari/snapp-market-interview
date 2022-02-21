package com.snapp.data


import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.snapp.data.mapper.ArticleMapper
import com.snapp.data.repository.ArticleCache
import com.snapp.data.repository.ArticleRemote
import com.snapp.domain.models.Article
import com.snapp.domain.repository.ArticleRepository
import com.snapp.domain.state.ArticleViewState
import com.snapp.domain.utils.RateLimiter
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleRemote: ArticleRemote,
    private val articleCache: ArticleCache,
    private val articleMapper: ArticleMapper
):ArticleRepository {

    override fun loadArticleBySourceId(
        sourceId: String,
        page: Int,
        pageSize: Int,
        fetchRequired: Boolean
    ): LiveData<ArticleViewState> {
        return object :
            NetworkBoundResource<List<Article>, List<Article>,ArticleViewState>() {
            override fun saveCallResult(item: List<Article>) {
                articleCache.insertArticles(item.map(articleMapper::mapToEntity))
            }

            override fun shouldFetch(data: List<Article>?): Boolean {
                return (fetchRequired && (data.isNullOrEmpty() || (data.isNotEmpty() && page > 0)))
            }

            override fun loadFromDb(): LiveData<List<Article>> {
                return articleCache.getArticlesBySourceId(sourceId).map {
                    it.map(articleMapper::mapFromEntity)
                }
            }

            override fun createCall(): Single<List<Article>> {
                return articleRemote.getArticles(
                    sourceId, page, pageSize
                ).map{
                    it.map(articleMapper::mapFromEntity)
                }
            }


        }.asLiveData
    }
}
