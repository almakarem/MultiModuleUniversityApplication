package com.example.universitylistapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.presentation.University

@Dao
interface UniversityDao {

    @Upsert
    suspend fun upsertAll(universities: List<UniversityEntity>)

    @Query("SELECT * FROM universityentity")
    fun paginSource(): PagingSource<Int,UniversityEntity>

    @Query("DELETE FROM universityentity")
    suspend fun clearAll()

}