package com.diegochancafe.worldcup.domain.usecase

import com.diegochancafe.worldcup.data.repository.MatchRepository
import com.diegochancafe.worldcup.domain.model.MatchModelDomain
import javax.inject.Inject


// -- Ready for injection
class GetMatchByGroupUseCase @Inject constructor(private val repository: MatchRepository) {
    // --
    suspend operator fun invoke(group: String): List<MatchModelDomain> {
        // --
        return repository.getMatchByGroupFromDatabase(group)
    }
}