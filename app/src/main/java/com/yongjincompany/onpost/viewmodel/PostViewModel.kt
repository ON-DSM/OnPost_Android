package com.yongjincompany.onpost.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.onpost.remote.response.SortPostResponse
import com.yongjincompany.onpost.repository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {
    var modo: MutableLiveData<Response<List<SortPostResponse>>> = MutableLiveData()

    fun getPost(sort: String, page: Int) {
        viewModelScope.launch {
            val response = postRepository.getPost(sort, page)
            modo.value = response
        }
    }
}