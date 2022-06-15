package com.company.data.remote.api

import com.company.data.remote.response.SortPostResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {
    @GET("post/main")
    suspend fun fetchPost(
        @Query("sort") sort:String,
        @Query("page") page: Int
    ): SortPostResponse
}