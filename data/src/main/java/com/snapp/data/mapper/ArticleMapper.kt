package com.snapp.data.mapper

import com.snapp.data.models.ArticleEntity
import com.snapp.domain.models.Article
import javax.inject.Inject

class ArticleMapper @Inject constructor(val sourceFakeMapper: SourceFakeMapper) :
    Mapper<ArticleEntity, Article> {

    override fun mapFromEntity(type: ArticleEntity): Article {
        return Article(
            type.author,
            type.title,
            type.description,
            type.url,
            type.urlToImage,
            type.publishedAt,
            type.content,
            sourceFakeMapper.mapFromEntity(type.source)
        )
    }

    override fun mapToEntity(type: Article): ArticleEntity {
        return ArticleEntity(
            type.author,
            type.title,
            type.description,
            type.url,
            type.urlToImage,
            type.publishedAt,
            type.content,
            sourceFakeMapper.mapToEntity(type.source)
        )
    }
}
