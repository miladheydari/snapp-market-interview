package com.snapp.cache.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.snapp.cache.models.SourceCacheEntity


@Dao
interface SourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplaceSources(sources: List<SourceCacheEntity>)

    @Query("SELECT * FROM source")
    fun getSources(): LiveData<List<SourceCacheEntity>>

    @Transaction
    fun deleteAndInsertSources(sources: List<SourceCacheEntity>) {
        deleteSources()
        insertReplaceSources(sources)
    }

    @Query("DELETE FROM source")
    fun deleteSources()
}