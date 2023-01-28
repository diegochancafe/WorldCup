package com.diegochancafe.worldcup.domain.model

import com.diegochancafe.worldcup.data.model.response.LoginModelResponse
import com.diegochancafe.worldcup.data.model.response.LoginToken
import java.io.Serializable

// --
data class LoginModelDomain(
    val status: String? = "",
    val data: LoginTokenDomain? = null,
    val message: String? = ""
): Serializable

// --
data class LoginTokenDomain(
    var token: String? = ""
): Serializable


// -- Extensions
fun LoginToken.toDomain() = LoginTokenDomain(token)
fun LoginModelResponse.toDomain() = LoginModelDomain(status, data?.toDomain(), message)