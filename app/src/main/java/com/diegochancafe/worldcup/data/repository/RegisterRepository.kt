package com.diegochancafe.worldcup.data.repository

import com.diegochancafe.worldcup.data.model.request.RegisterModelRequest
import com.diegochancafe.worldcup.data.model.response.LoginModelResponse
import com.diegochancafe.worldcup.data.network.service.RegisterService
import com.diegochancafe.worldcup.domain.model.LoginModelDomain
import com.diegochancafe.worldcup.domain.model.toDomain
import javax.inject.Inject

// -- Ready for injection
class RegisterRepository @Inject constructor(
    // -- Injections
    private val api: RegisterService
) {
    // --
    suspend fun postRegisterFromApi(request: RegisterModelRequest): LoginModelDomain? {
        // --
        val response: LoginModelResponse? = api.postRegister(request)
        return response?.toDomain()
    }
}