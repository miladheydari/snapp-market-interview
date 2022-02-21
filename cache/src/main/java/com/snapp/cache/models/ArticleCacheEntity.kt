package com.snapp.cache.models

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "article", primaryKeys = ["publishedAt", "title"])
data class ArticleCacheEntity(
    var author: String? = "",
    var title: String = "",
    var description: String? = "",
    var url: String? = "",
    var urlToImage: String? = "",
    var publishedAt: String = "",
    var content: String? = "",
    @Embedded var source: SourceFakeCacheEntity
)

data class SourceFakeCacheEntity(
    var id: String = "",
    var name: String = ""
)
