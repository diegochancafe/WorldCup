package com.diegochancafe.worldcup.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.diegochancafe.worldcup.data.database.dao.IMatchDao
import com.diegochancafe.worldcup.data.database.dao.ITeamDao
import com.diegochancafe.worldcup.data.database.entities.MatchModelEntity
import com.diegochancafe.worldcup.data.database.entities.TeamModelEntity

// --
@Database(entities = [TeamModelEntity::class, MatchModelEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    // --
    abstract fun getTeamDao(): ITeamDao
    abstract fun getMatchDao(): IMatchDao
}