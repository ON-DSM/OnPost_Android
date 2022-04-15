package com.yongjincompany.data.remote.response.auth

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
