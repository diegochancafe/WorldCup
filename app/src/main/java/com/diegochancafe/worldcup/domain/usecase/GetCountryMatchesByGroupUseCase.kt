package com.diegochancafe.worldcup.domain.usecase

import com.diegochancafe.worldcup.data.repository.MatchRepository
import com.diegochancafe.worldcup.domain.model.MatchModelDomain
import javax.inject.Inject


// -- Ready for injection
class GetCountryMatchesByGroupUseCase @Inject constructor(private val repository: MatchRepository) {
    // --
    suspend operator fun invoke(group: String, country: String): List<MatchModelDomain> {
        // --
        return repository.getCountryMatchesByGroupFromDatabase(group, country)
    }
}