package com.miladheydari.snappmarketandroidtest.domain.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article", primaryKeys = ["publishedAt", "title"])
data class Article(
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
    @Embedded var source: SourceFake
)

data class SourceFake(
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = ""
)
