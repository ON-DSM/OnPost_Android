package com.yongjincompany.onpost.remote.response

data class ReadPostResponse(
    val comments: List<Comment>,
    val content: String,
    val createAt: String,
    val doLike: Boolean,
    val id: Int,
    val images: List<String>,
    val introduce: String,
    val like: Int,
    val profile: String,
    val tags: String,
    val title: String,
    val writer: Writer,
) {
    data class Comment(
        val content: String,
        val createAt: String,
        val id: Int,
        val moreComment: Int,
        val writer: Writer,
    )

    data class Writer(
        val email: String,
        val introduce: String,
        val name: String,
        val profile: String,
    )
}