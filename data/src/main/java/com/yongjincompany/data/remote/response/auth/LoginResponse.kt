package com.yongjincompany.data.remote.response.auth

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)