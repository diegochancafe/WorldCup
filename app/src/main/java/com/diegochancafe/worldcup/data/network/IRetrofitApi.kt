package com.diegochancafe.worldcup.data.network

import com.diegochancafe.worldcup.data.model.request.LoginModelRequest
import com.diegochancafe.worldcup.data.model.response.LoginModelResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

// --
interface IRetrofitApi {
    // --
    @POST("user/login")
    suspend fun postLogin(
        @Body request: LoginModelRequest
    ): Response<LoginModelResponse?>
}