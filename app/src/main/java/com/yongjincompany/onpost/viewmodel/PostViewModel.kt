package com.yongjincompany.onpost.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.onpost.remote.response.SortPostResponse
import com.yongjincompany.onpost.remote.response.TopThreePostResponse
import com.yongjincompany.onpost.repository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {
    var sortPostLiveData: MutableLiveData<Response<List<SortPostResponse>>> = MutableLiveData()
    var topThreePostLiveData: MutableLiveData<Response<List<TopThreePostResponse>>> = MutableLiveData()

    fun getPost(sort: String, page: Int) {
        viewModelScope.launch {
            val response = postRepository.getPost(sort, page)
            sortPostLiveData.value = response
        }
    }

    fun getTopThreePost(sort: String) {
        viewModelScope.launch {
            val response = postRepository.getTopThreePost(sort)
            topThreePostLiveData.value = response
        }
    }
}