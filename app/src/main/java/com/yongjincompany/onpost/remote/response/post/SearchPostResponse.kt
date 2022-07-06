package com.yongjincompany.onpost.remote.response.post

data class SearchPostResponse(
    val createAt: String,
    val id: Int,
    val introduce: String,
    val like: Int,
    val profileImage: String,
    val tags: String,
    val title: String,
    val writer: Writer
) {
    data class Writer(
        val name: String,
        val profile: String
    )
}