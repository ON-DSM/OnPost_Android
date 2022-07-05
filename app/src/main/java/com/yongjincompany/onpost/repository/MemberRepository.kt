package com.yongjincompany.onpost.repository

import com.yongjincompany.onpost.remote.RetrofitBuilder
import com.yongjincompany.onpost.remote.response.member.SearchUserResponse
import com.yongjincompany.onpost.remote.response.post.SortPostResponse
import retrofit2.Response

class MemberRepository {
    suspend fun getSearchUser(email: String): Response<SearchUserResponse> {
        return RetrofitBuilder.memberApi.fetchSearchUser(email)
    }
}