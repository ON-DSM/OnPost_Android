package com.yongjincompany.onpost.viewmodel.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yongjincompany.onpost.repository.MemberRepository


class MemberViewModelFactory(private val memberRepository: MemberRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MemberViewModel(memberRepository) as T
    }
}