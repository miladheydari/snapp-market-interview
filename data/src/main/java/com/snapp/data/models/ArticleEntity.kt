package com.snapp.data.models



data class ArticleEntity(
    var author: String? = "",
    var title: String = "",
    var description: String? = "",
    var url: String? = "",
    var urlToImage: String? = "",
    var publishedAt: String = "",
    var content: String? = "",
     var source: SourceFakeEntity
)
