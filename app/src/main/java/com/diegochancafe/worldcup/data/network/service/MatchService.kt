package com.diegochancafe.worldcup.data.network.service

import com.diegochancafe.worldcup.data.model.response.MatchModelResponse
import com.diegochancafe.worldcup.data.model.singleton.TokenSingleton
import com.diegochancafe.worldcup.data.network.IRetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


// -- Ready for injection
class MatchService @Inject constructor(
    private val api: IRetrofitApi,
    private val tokenSingleton: TokenSingleton
) {
    // --
    suspend fun getMatchById(id: String): MatchModelResponse? {
        // --
        return withContext(Dispatchers.IO) {
            // --
            try {
                // --
                val response = api.getMatchById(id, tokenSingleton.loginTokenDomain?.token!!)
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