package com.diegochancafe.worldcup.data.network.service

import com.diegochancafe.worldcup.data.model.response.TeamModelResponse
import com.diegochancafe.worldcup.data.model.singleton.TokenSingleton
import com.diegochancafe.worldcup.data.network.IRetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


// -- Ready for injection
class TeamService @Inject constructor(
    private val api: IRetrofitApi,
    private val tokenSingleton: TokenSingleton
) {
    // --
    suspend fun getTeam(): TeamModelResponse? {
        // --
        return withContext(Dispatchers.IO) {
            // --
            try {
                // --
                val response = api.getTeam(tokenSingleton.loginTokenDomain?.token!!)
                // --
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}