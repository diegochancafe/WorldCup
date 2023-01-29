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
    @Query("SELECT * FROM match_table WHERE group_name = :group AND (home_team_en = :country OR away_team_en = :country)")
    suspend fun getCountryMatchesByGroup(group: String, country: String): List<MatchModelEntity>

    // --
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<MatchModelEntity>)

    // --
    @Query("DELETE FROM match_table")
    suspend fun deleteAll()
}