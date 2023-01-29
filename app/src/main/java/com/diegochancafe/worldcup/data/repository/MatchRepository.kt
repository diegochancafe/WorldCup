package com.diegochancafe.worldcup.data.repository

import com.diegochancafe.worldcup.data.model.response.MatchData
import com.diegochancafe.worldcup.data.network.service.MatchService
import com.diegochancafe.worldcup.domain.model.MatchModelDomain
import com.diegochancafe.worldcup.domain.model.toDomain
import javax.inject.Inject

// --
class MatchRepository @Inject constructor(
    // -- Injections
    private val api: MatchService
) {
    // --
    suspend fun getMatchByIdFromApi(id: String): List<MatchModelDomain> {
        // --
        val result = api.getMatchById(id)
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
}