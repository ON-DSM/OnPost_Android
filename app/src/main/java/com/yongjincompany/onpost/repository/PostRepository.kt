package com.yongjincompany.onpost.repository

import com.yongjincompany.onpost.remote.RetrofitBuilder
import com.yongjincompany.onpost.remote.response.ReadPostResponse
import com.yongjincompany.onpost.remote.response.SortPostResponse
import com.yongjincompany.onpost.remote.response.TopThreePostResponse
import retrofit2.Response

class PostRepository {
    suspend fun getPost(sort: String, page: Int): Response<List<SortPostResponse>> {
        return RetrofitBuilder.postApi.fetchPost(sort, page)
    }
    suspend fun getTopThreePost(sort: String): Response<List<TopThreePostResponse>> {
        return RetrofitBuilder.postApi.fetchTopPost(sort)
    }
    suspend fun getShowPost(id: Int): Response<ReadPostResponse> {
        return RetrofitBuilder.postApi.fetchShowPost(id)
    }
}