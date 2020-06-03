package com.miladheydari.snappmarketandroidtest.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "source")
data class Source(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: String = "",
    @SerializedName("name")
    var name:String = "",
    @SerializedName("description")
    var description:String? = "",
    @SerializedName("url")
    var url:String? = "",
    @SerializedName("category")
    var category:String? = "",
    @SerializedName("language")
    var language:String? = "",
    @SerializedName("country")
    var country:String? = "")

