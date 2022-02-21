package com.snapp.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "source")
data class SourceCacheEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String = "",
    var name:String = "",
    var description:String? = "",
    var url:String? = "",
    var category:String? = "",
    var language:String? = "",
    var country:String? = "")

