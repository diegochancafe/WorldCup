package com.diegochancafe.worldcup.domain.usecase

import com.diegochancafe.worldcup.data.database.entities.toDatabase
import com.diegochancafe.worldcup.data.repository.MatchRepository
import com.diegochancafe.worldcup.domain.model.MatchModelDomain
import javax.inject.Inject

// --
class GetMatchUseCase @Inject constructor(private val repository: MatchRepository) {
    // --
    suspend operator fun invoke(): List<MatchModelDomain> {
        // --
        val resultDatabase = repository.getMatchFromDatabase()
        // --
        return resultDatabase.ifEmpty {
            // --
            val resultApi = repository.getMatchFromApi()
            // --
            repository.deleteAll()
            repository.insertMatchData(resultApi.map { it.toDatabase() })
            resultApi
        }
    }
}