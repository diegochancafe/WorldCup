package com.diegochancafe.worldcup.data.model.request

import java.io.Serializable

// --
data class LoginModelRequest(
    val email: String,
    val password: String
): Serializable
