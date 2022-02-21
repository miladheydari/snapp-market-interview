package com.snapp.domain.repository

import androidx.lifecycle.LiveData
import com.snapp.domain.state.SourceViewState


interface SourceRepository {

    fun loadSource(fetchRequired: Boolean): LiveData<SourceViewState>
}
