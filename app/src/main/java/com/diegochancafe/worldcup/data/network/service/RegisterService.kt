package com.diegochancafe.worldcup.data.network.service

import com.diegochancafe.worldcup.data.model.request.LoginModelRequest
import com.diegochancafe.worldcup.data.model.request.RegisterModelRequest
import com.diegochancafe.worldcup.data.model.response.LoginModelResponse
import com.diegochancafe.worldcup.data.network.IRetrofitApi
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// -- Ready for injection
class RegisterService @Inject constructor(private val api: IRetrofitApi) {
    // --
    suspend fun postRegister(request: RegisterModelRequest): LoginModelResponse? {
        // --
        return withContext(Dispatchers.IO) {
            // --
            try {
                // --
                val response = api.postRegister(request)
                // --
                if (response.isSuccessful) {
                    response.body()
                } else {
                    // --
                    try {
                        // --
                        val gson = GsonBuilder().create()
                        val loginModelResponse = gson.fromJson(
                            response.errorBody()?.string(),
                            LoginModelResponse::class.java
                        )
                        // --
                        loginModelResponse

                    } catch (e: Exception) {
                        null
                    }
                }
            } catch (e: Exception) {
                null
            }
        }
    }
}