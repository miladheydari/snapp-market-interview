package com.snapp.data.repository

import com.snapp.data.models.SourceEntity
import io.reactivex.Single


interface SourceRemote {

    fun getSources(): Single<List<SourceEntity>>
}
