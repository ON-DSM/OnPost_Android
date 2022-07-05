package com.yongjincompany.onpost.viewmodel.member

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yongjincompany.onpost.remote.response.member.SearchUserResponse
import com.yongjincompany.onpost.remote.response.post.ReadPostResponse
import com.yongjincompany.onpost.remote.response.post.SearchPostResponse
import com.yongjincompany.onpost.remote.response.post.SortPostResponse
import com.yongjincompany.onpost.remote.response.post.TopThreePostResponse
import com.yongjincompany.onpost.repository.MemberRepository
import com.yongjincompany.onpost.repository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MemberViewModel(private val memberRepository: MemberRepository) : ViewModel() {
    var searchUserLiveData: MutableLiveData<Response<SearchUserResponse>> = MutableLiveData()

    fun getSearchUser(email: String) {
        viewModelScope.launch {
            val response = memberRepository.getSearchUser(email)
            searchUserLiveData.value = response
        }
    }

}