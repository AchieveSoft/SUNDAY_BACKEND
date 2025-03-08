package com.achievesoft.sunday.models.requests.auth

data class LoginRequest(
    val email: String,
    val password: String
)
