package com.miladheydari.snappmarketandroidtest.domain.model

import com.google.gson.annotations.SerializedName

data class ArticleList(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Article>
)