package com.diegochancafe.worldcup.data.repository

import com.diegochancafe.worldcup.data.model.request.LoginModelRequest
import com.diegochancafe.worldcup.data.model.response.LoginModelResponse
import com.diegochancafe.worldcup.data.network.service.LoginService
import com.diegochancafe.worldcup.domain.model.LoginModelDomain
import com.diegochancafe.worldcup.domain.model.toDomain
import javax.inject.Inject

// -- Ready for injection
class LoginRepository @Inject constructor(
    // -- Injections
    private val api: LoginService
) {
    // --
    suspend fun postLoginFromApi(request: LoginModelRequest): LoginModelDomain? {
        // --
        val response: LoginModelResponse? = api.postLogin(request)
        return response?.toDomain()
    }
}