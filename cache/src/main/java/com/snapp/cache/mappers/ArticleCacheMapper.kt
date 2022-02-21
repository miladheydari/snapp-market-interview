package com.snapp.cache.mappers

import com.snapp.cache.models.ArticleCacheEntity
import com.snapp.data.models.ArticleEntity
import javax.inject.Inject

class ArticleCacheMapper @Inject constructor(private val sourceFakeEntityMapper: SourceFakeCacheMapper) :
    CacheMapper<ArticleCacheEntity, ArticleEntity> {


    override fun mapFromCached(type: ArticleCacheEntity): ArticleEntity {
        return ArticleEntity(
            type.author,
            type.title,
            type.description,
            type.url,
            type.urlToImage,
            type.publishedAt,
            type.content,
            sourceFakeEntityMapper.mapFromCached(type.source)
        )
    }

    override fun mapToCached(type: ArticleEntity): ArticleCacheEntity {
        return ArticleCacheEntity(
            type.author,
            type.title,
            type.description,
            type.url,
            type.urlToImage,
            type.publishedAt,
            type.content,
            sourceFakeEntityMapper.mapToCached(type.source)
        )
    }
}
