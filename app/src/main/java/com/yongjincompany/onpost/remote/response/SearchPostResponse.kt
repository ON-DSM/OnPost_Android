package com.yongjincompany.onpost.remote.response

data class SearchPostResponse(
    val comments: Int,
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
        val email: String,
        val introduce: String,
        val name: String,
        val profile: String
    )
}