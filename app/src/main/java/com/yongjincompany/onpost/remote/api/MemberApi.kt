package com.yongjincompany.onpost.remote.api

import com.yongjincompany.onpost.remote.response.member.SearchUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MemberApi {
    @GET("member/profile")
    suspend fun fetchSearchUser(
        @Query ("email") email: String
    ): Response<SearchUserResponse>
}