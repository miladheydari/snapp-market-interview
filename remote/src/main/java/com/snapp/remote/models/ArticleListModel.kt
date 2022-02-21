package com.snapp.remote.models

import com.google.gson.annotations.SerializedName

data class ArticleListModel(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<ArticleModel>
)