package com.snapp.data.models

data class SourceEntity(
    var id: String = "",
    var name:String = "",
    var description:String? = "",
    var url:String? = "",
    var category:String? = "",
    var language:String? = "",
    var country:String? = "")

