package com.diegochancafe.worldcup.domain.usecase

import com.diegochancafe.worldcup.data.database.entities.toDatabase
import com.diegochancafe.worldcup.data.repository.TeamRepository
import com.diegochancafe.worldcup.domain.model.TeamModelDomain
import javax.inject.Inject

// --
class GetTeamUseCase @Inject constructor(private val repository: TeamRepository) {
    // --
    suspend operator fun invoke(): List<TeamModelDomain> {
        // --
        val resultDatabase = repository.getTeamFromDatabase()
        // --
        return resultDatabase.ifEmpty {
            // --
            val resultApi = repository.getTeamFromApi()
            // --
            repository.deleteAll()
            repository.insertTeamData(resultApi.map { it.toDatabase() })
            resultApi
        }
    }
}