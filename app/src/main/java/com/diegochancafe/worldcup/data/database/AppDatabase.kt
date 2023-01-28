package com.diegochancafe.worldcup.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diegochancafe.worldcup.data.database.dao.ITeamDao
import com.diegochancafe.worldcup.data.database.entities.TeamModelEntity

// --
@Database(entities = [TeamModelEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    // --
    abstract fun getTeamDao(): ITeamDao
}