package com.diegochancafe.worldcup.data.model.request

data class RegisterModelRequest (
    val name: String,
    val email: String,
    val password: String,
    val passwordConfirm: String
)