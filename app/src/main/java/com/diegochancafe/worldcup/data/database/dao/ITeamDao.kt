package com.diegochancafe.worldcup.data.database.dao

import androidx.room.*
import com.diegochancafe.worldcup.data.database.entities.TeamModelEntity


// --
@Dao
interface ITeamDao {
    // --
    @Query("SELECT * FROM team_table")
    suspend fun getAll(): List<TeamModelEntity>

    // --
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<TeamModelEntity>)

    // --
    @Query("DELETE FROM team_table")
    suspend fun deleteAll()
}