package com.diegochancafe.worldcup.data.repository

import com.diegochancafe.worldcup.data.database.dao.IMatchDao
import com.diegochancafe.worldcup.data.database.entities.MatchModelEntity
import com.diegochancafe.worldcup.data.model.response.MatchData
import com.diegochancafe.worldcup.data.network.service.MatchService
import com.diegochancafe.worldcup.domain.model.MatchModelDomain
import com.diegochancafe.worldcup.domain.model.toDomain
import javax.inject.Inject

// --
class MatchRepository @Inject constructor(
    // -- Injections
    private val api: MatchService,
    private val matchDao: IMatchDao
) {
    // --
    suspend fun getMatchFromApi(): List<MatchModelDomain> {
        // --
        val result = api.getMatch()
        // --
        return if (result != null) {
            // --
            if (result.data?.isNotEmpty() == true) {
                // --
                val response: List<MatchData> = result.data
                response.map { it.toDomain() }
            } else {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    // --
    suspend fun getMatchFromDatabase(): List<MatchModelDomain>  {
        // --
        val response: List<MatchModelEntity> = matchDao.getAll()
        return response.map { it.toDomain() }
    }

    // --
    suspend fun getMatchByGroupFromDatabase(group: String): List<MatchModelDomain>  {
        // --
        val response: List<MatchModelEntity> = matchDao.getMatchByGroup(group)
        return response.map { it.toDomain() }
    }

    // --
    suspend fun getCountryMatchesByGroupFromDatabase(group: String, country: String): List<MatchModelDomain>  {
        // --
        val response: List<MatchModelEntity> = matchDao.getCountryMatchesByGroup(group, country)
        return response.map { it.toDomain() }
    }


    // --
    suspend fun insertMatchData(list: List<MatchModelEntity>) {
        // --
        matchDao.insertAll(list)
    }

    // --
    suspend fun deleteAll() {
        // --
        matchDao.deleteAll()
    }
}