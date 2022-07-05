package com.yongjincompany.onpost.remote.response.post

data class SortPostResponse(
    val id: Int,
    val content: String,
    val title: String,
    val introduce: String,
    val writer: Writer,
    val like: Int,
    val comments: Int,
    val tags: String,
    val profileImage: String,
    val createAt: String,

    ) {
    data class Writer(
        val profile: String,
        val name: String,
    )
}

