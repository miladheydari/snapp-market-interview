package com.snapp.data.repository

import androidx.lifecycle.LiveData
import com.snapp.data.models.SourceEntity


interface SourceCache {

    fun getSources(): LiveData<List<SourceEntity>>
    fun insertSources(sources: List<SourceEntity>)

}
