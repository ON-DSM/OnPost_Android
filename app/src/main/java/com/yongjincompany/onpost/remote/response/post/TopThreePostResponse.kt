package com.yongjincompany.onpost.remote.response.post

data class TopThreePostResponse(
    val comments: Int,
    val createAt: String,
    val id: Int,
    val like: Int,
    val rank: Int,
    val profileImage: String,
    val title: String,
    val writer: Writer
) {
    data class Writer(
        val name: String
    )
}