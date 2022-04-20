package com.yongjincompany.popo.remote.response

data class SortPostResponse(
    val PostList: ArrayList<SortPostResponseItem>
) {
    data class SortPostResponseItem(
        val comments: Int,
        val context: String,
        val createAt: String,
        val id: Int,
        val image: Image,
        val like: Int,
        val title: String,
        val writer: Writer
    )

    data class Image(
        val id: Int,
        val imagePath: String
    )

    data class Writer(
        val email: String,
        val id: Int,
        val image: Any, // null
        val introduce: Any, //null
        val name: String
    )
}
