package com.yongjincompany.popo.api

import com.yongjincompany.popo.remote.response.SortPostResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {
    @GET("post/main")
    fun SortPost(
        @Query("sort") sort: String,
        @Query("page") page: String
    ): Single<List<SortPostResponse>>
}