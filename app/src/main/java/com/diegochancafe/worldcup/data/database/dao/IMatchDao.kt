package com.diegochancafe.worldcup.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diegochancafe.worldcup.data.database.entities.MatchModelEntity

@Dao
interface IMatchDao {
    // --
    @Query("SELECT * FROM match_table")
    suspend fun getAll(): List<MatchModelEntity>

    // --
    @Query("SELECT * FROM match_table WHERE group_name = :group")
    suspend fun getMatchByGroup(group: String): List<MatchModelEntity>

    // --
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<MatchModelEntity>)

    // --
    @Query("DELETE FROM match_table")
    suspend fun deleteAll()
}