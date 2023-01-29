package com.diegochancafe.worldcup.domain.usecase

import com.diegochancafe.worldcup.data.model.request.RegisterModelRequest
import com.diegochancafe.worldcup.data.repository.RegisterRepository
import com.diegochancafe.worldcup.domain.model.LoginModelDomain
import javax.inject.Inject

// -- Ready for injection
class PostRegisterUseCase @Inject constructor(private val repository: RegisterRepository) {
    // --
    suspend operator fun invoke(request: RegisterModelRequest): LoginModelDomain? {
        // --
        return repository.postRegisterFromApi(request)
    }
}