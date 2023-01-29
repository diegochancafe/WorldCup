package com.diegochancafe.worldcup.data.network

import com.diegochancafe.worldcup.data.model.request.LoginModelRequest
import com.diegochancafe.worldcup.data.model.request.RegisterModelRequest
import com.diegochancafe.worldcup.data.model.response.LoginModelResponse
import com.diegochancafe.worldcup.data.model.response.MatchModelResponse
import com.diegochancafe.worldcup.data.model.response.TeamModelResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

// --
interface IRetrofitApi {
    // --
    @POST("user/login")
    suspend fun postLogin(
        @Body request: LoginModelRequest
    ): Response<LoginModelResponse?>

    // --
    @GET("team")
    suspend fun getTeam(
        @Header("Authorization") token: String
    ): Response<TeamModelResponse?>

    // --
    @GET("match/")
    suspend fun getMatch(
        @Header("Authorization") token: String
    ): Response<MatchModelResponse?>

    // --
    @POST("user")
    suspend fun postRegister(
        @Body request: RegisterModelRequest
    ): Response<LoginModelResponse?>
}