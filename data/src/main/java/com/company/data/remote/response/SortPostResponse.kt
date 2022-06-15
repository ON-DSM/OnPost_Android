package com.company.data.remote.response

data class SortPostResponse(
    val PostList: ArrayList<SortPostResponseItem>
) {
    data class SortPostResponseItem(
        val id: Int,
        val content: String,
        val title: String,
        val introduce: String,
        val writer: Writer,
        val like: Int,
        val comments: Int,
        val tags: List<String>,
        val profileImage: String,
        val createAt: String,

        )

    data class Writer(
        val profile: String,
        val email: String,
        val name: String,
        val introduce: String
    )
}
