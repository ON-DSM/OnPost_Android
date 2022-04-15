package com.yongjincompany.data.remote.api

import com.yongjincompany.data.remote.request.auth.LoginRequest
import com.yongjincompany.data.remote.request.auth.RegisterRequest
import com.yongjincompany.data.remote.response.auth.LoginResponse
import com.yongjincompany.data.remote.response.auth.TokenResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun authLogin(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("auth/signup")
    suspend fun authSignUp(
        @Body registerRequest: RegisterRequest
    )

    @PATCH("auth/token")
    suspend fun reissueToken(
        @Header("REFRESHTOKEN") refreshToken: String
    ): TokenResponse
}