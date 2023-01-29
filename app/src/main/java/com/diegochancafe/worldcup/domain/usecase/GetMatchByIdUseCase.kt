package com.diegochancafe.worldcup.domain.usecase

import com.diegochancafe.worldcup.data.repository.MatchRepository
import com.diegochancafe.worldcup.domain.model.MatchModelDomain
import javax.inject.Inject

// --
class GetMatchByIdUseCase @Inject constructor(private val repository: MatchRepository) {
    // --
    suspend operator fun invoke(id: String): List<MatchModelDomain> {
        // --
        return repository.getMatchByIdFromApi(id)
    }
}