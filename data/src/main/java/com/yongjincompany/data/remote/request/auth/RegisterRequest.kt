package com.yongjincompany.data.remote.request.auth

data class RegisterRequest(
    val email: String,
    val password: String,
    val username: String
)