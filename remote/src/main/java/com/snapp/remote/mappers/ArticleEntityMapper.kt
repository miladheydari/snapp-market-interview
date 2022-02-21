package com.snapp.remote.mappers

import com.snapp.data.models.ArticleEntity
import com.snapp.remote.models.ArticleModel
import javax.inject.Inject

class ArticleEntityMapper @Inject constructor(private val sourceFakeEntityMapper: SourceFakeEntityMapper) :
    EntityMapper<ArticleModel, ArticleEntity> {

    override fun mapFromModel(model: ArticleModel): ArticleEntity {
        return ArticleEntity(
            model.author,
            model.title,
            model.description,
            model.url,
            model.urlToImage,
            model.publishedAt,
            model.content,
            sourceFakeEntityMapper.mapFromModel(model.source)
        )
    }
}
