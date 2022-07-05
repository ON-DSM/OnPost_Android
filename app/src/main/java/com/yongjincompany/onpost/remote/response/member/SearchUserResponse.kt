package com.yongjincompany.onpost.remote.response.member

data class SearchUserResponse(
    val createAt: String,
    val follower: Int,
    val following: Int,
    val introduce: String,
    val name: String,
    val profile: String
)