package com.snapp.remote.models

import com.google.gson.annotations.SerializedName

data class ArticleModel(
    @SerializedName("author")
    var author: String? = "",
    @SerializedName("title")
    var title: String = "",
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("url")
    var url: String? = "",
    @SerializedName("urlToImage")
    var urlToImage: String? = "",
    @SerializedName("publishedAt")
    var publishedAt: String = "",
    @SerializedName("content")
    var content: String? = "",
    @SerializedName("source")
    var source: SourceFakeModel
)

data class SourceFakeModel(
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = ""
)
