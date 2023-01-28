package com.diegochancafe.worldcup.data.repository

import com.diegochancafe.worldcup.data.database.dao.ITeamDao
import com.diegochancafe.worldcup.data.database.entities.TeamModelEntity
import com.diegochancafe.worldcup.data.model.response.TeamData
import com.diegochancafe.worldcup.data.network.service.TeamService
import com.diegochancafe.worldcup.domain.model.TeamModelDomain
import com.diegochancafe.worldcup.domain.model.toDomain
import javax.inject.Inject


class TeamRepository @Inject constructor(
    // -- Injections
    private val api: TeamService,
    private val teamDao: ITeamDao
) {
    // --
    suspend fun getTeamFromApi(): List<TeamModelDomain> {
        // --
        val result = api.getTeam()
        // --
        return if (result != null) {
            // --
            if (result.data?.isNotEmpty() == true) {
                // --
                val response: List<TeamData> = result.data
                response.map { it.toDomain() }
            } else {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    // --
    suspend fun getTeamFromDatabase(): List<TeamModelDomain>  {
        // --
        val response: List<TeamModelEntity> = teamDao.getAll()
        return response.map { it.toDomain() }
    }

    // --
    suspend fun insertTeamData(list: List<TeamModelEntity>) {
        // --
        teamDao.insertAll(list)
    }

    // --
    suspend fun deleteAll() {
        // --
        teamDao.deleteAll()
    }
}
