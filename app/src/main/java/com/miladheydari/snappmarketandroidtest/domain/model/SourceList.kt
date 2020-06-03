package com.miladheydari.snappmarketandroidtest.domain.model

import com.google.gson.annotations.SerializedName


data class SourceList(
    @SerializedName("status") val status: String,
    @SerializedName("sources") val sources: List<Source>
    )