package com.snapp.remote.models

import com.google.gson.annotations.SerializedName


data class SourceListModel(
    @SerializedName("status") val status: String,
    @SerializedName("sources") val sources: List<SourceModel>
    )