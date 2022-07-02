package com.yongjincompany.onpost.repository

import com.yongjincompany.onpost.remote.RetrofitBuilder
import com.yongjincompany.onpost.remote.response.SortPostResponse
import retrofit2.Response

class PostRepository {
    suspend fun getPost(sort: String, page: Int): Response<List<SortPostResponse>> {
        return RetrofitBuilder.postApi.fetchPost(sort, page)
    }
}