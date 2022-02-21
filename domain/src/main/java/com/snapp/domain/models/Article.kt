package com.snapp.domain.models



data class Article(
    var author: String? = "",
    var title: String = "",
    var description: String? = "",
    var url: String? = "",
    var urlToImage: String? = "",
    var publishedAt: String = "",
    var content: String? = "",
     var source: SourceFake
)
