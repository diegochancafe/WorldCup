package com.diegochancafe.worldcup.data.model.response

// --
data class LoginModelResponse(
    val status: String?,
    val data: LoginToken?,
    val message: String?
)

// --
data class LoginToken(
    val token: String?
)
