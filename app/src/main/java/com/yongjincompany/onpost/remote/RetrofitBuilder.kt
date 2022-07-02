package com.yongjincompany.onpost.remote

import com.yongjincompany.onpost.remote.api.PostApi
import com.yongjincompany.onpost.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postApi: PostApi = retrofit.create(PostApi::class.java)
}