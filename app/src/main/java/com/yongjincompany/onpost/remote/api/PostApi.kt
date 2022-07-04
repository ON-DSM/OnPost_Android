package com.yongjincompany.onpost.remote.api

import com.yongjincompany.onpost.remote.response.ReadPostResponse
import com.yongjincompany.onpost.remote.response.SearchPostResponse
import com.yongjincompany.onpost.remote.response.SortPostResponse
import com.yongjincompany.onpost.remote.response.TopThreePostResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {
    @GET("post/main")
    suspend fun fetchPost(
        @Query("sort") sort: String,
        @Query("page") page: Int,
    ): Response<List<SortPostResponse>>

    @GET("post/top3")
    suspend fun fetchTopPost(
        @Query("sort") sort: String
    ): Response<List<TopThreePostResponse>>

    @GET("post/show")
    suspend fun fetchShowPost(
        @Query("id") id: Int
    ): Response<ReadPostResponse>

    @GET("post/search")
    suspend fun fetchSearchPost(
        @Query("param") param: String
    ): Response<List<SearchPostResponse>>
}