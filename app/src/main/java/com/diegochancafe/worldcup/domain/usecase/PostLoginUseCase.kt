package com.diegochancafe.worldcup.domain.usecase

import com.diegochancafe.worldcup.data.model.request.LoginModelRequest
import com.diegochancafe.worldcup.data.repository.LoginRepository
import com.diegochancafe.worldcup.domain.model.LoginModelDomain
import javax.inject.Inject

// -- Ready for injection
class PostLoginUseCase @Inject constructor(private val repository: LoginRepository) {
    // --
    suspend operator fun invoke(request: LoginModelRequest): LoginModelDomain? {
        // --
        return repository.postLoginFromApi(request)
    }
}