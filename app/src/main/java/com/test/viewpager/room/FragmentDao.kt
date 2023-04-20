package com.test.viewpager.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FragmentDao {
    @Query("SELECT * FROM fragment_table")
    fun getAllFragments(): LiveData<List<FragmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFragment(fragment: FragmentEntity)
}