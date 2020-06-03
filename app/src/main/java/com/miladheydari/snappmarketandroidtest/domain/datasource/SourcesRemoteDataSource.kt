package com.miladheydari.snappmarketandroidtest.domain.datasource


import com.miladheydari.snappmarketandroidtest.domain.TestAppAPI
import com.miladheydari.snappmarketandroidtest.domain.model.SourceList

import io.reactivex.Single
import javax.inject.Inject

class SourcesRemoteDataSource @Inject constructor(private val api: TestAppAPI) {

    fun getSources(): Single<SourceList> {


        return api.getSources()
    }
}
