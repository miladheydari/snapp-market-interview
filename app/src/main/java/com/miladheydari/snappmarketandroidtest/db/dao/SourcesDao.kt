package com.miladheydari.snappmarketandroidtest.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.miladheydari.snappmarketandroidtest.domain.model.Source


@Dao
interface SourcesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplaceSources(sources: List<Source>)

    @Query("SELECT * FROM source")
    fun getSources(): LiveData<List<Source>>




    @Transaction
    fun deleteAndInsertSources(sources: List<Source>) {
        deleteSources()
        insertReplaceSources(sources)
    }

    @Query("DELETE FROM source")
    fun deleteSources()
}