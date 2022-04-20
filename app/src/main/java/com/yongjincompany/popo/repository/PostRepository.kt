package com.yongjincompany.popo.repository

import com.yongjincompany.popo.api.PostApi
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postApi: PostApi
) {
    fun getPostInfo(sort: String, page: String) = postApi.SortPost(sort, page)
}