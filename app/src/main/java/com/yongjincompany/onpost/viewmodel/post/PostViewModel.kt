package com.yongjincompany.onpost.viewmodel.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.onpost.remote.response.post.ReadPostResponse
import com.yongjincompany.onpost.remote.response.post.SearchPostResponse
import com.yongjincompany.onpost.remote.response.post.SortPostResponse
import com.yongjincompany.onpost.remote.response.post.TopThreePostResponse
import com.yongjincompany.onpost.repository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {
    var sortPostLiveData: MutableLiveData<Response<List<SortPostResponse>>> = MutableLiveData()
    var topThreePostLiveData: MutableLiveData<Response<List<TopThreePostResponse>>> = MutableLiveData()
    var showPostLiveData: MutableLiveData<Response<ReadPostResponse>> = MutableLiveData()
    var searchPostLiveData: MutableLiveData<Response<List<SearchPostResponse>>> = MutableLiveData()

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

    fun getShowPost(id: Int) {
        viewModelScope.launch {
            val response = postRepository.getShowPost(id)
            showPostLiveData.value = response
        }
    }

    fun getSearchPost(param: String) {
        viewModelScope.launch {
            val response = postRepository.getSearchPost(param)
            searchPostLiveData.value = response
        }
    }
}