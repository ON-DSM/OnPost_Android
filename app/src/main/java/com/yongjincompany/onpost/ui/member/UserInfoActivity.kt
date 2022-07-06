package com.yongjincompany.onpost.ui.member

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.yongjincompany.onpost.R
import com.yongjincompany.onpost.databinding.ActivityMainBinding
import com.yongjincompany.onpost.databinding.ActivityPostDetailBinding
import com.yongjincompany.onpost.databinding.ActivityUserInfoBinding
import com.yongjincompany.onpost.repository.MemberRepository
import com.yongjincompany.onpost.repository.PostRepository
import com.yongjincompany.onpost.viewmodel.member.MemberViewModel
import com.yongjincompany.onpost.viewmodel.member.MemberViewModelFactory
import com.yongjincompany.onpost.viewmodel.post.PostViewModel
import com.yongjincompany.onpost.viewmodel.post.PostViewModelFactory

class UserInfoActivity : AppCompatActivity() {

    private lateinit var vm: MemberViewModel
    private lateinit var binding: ActivityUserInfoBinding
    var data: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = MemberRepository()
        val viewModelFactory = MemberViewModelFactory(repository)
        vm = ViewModelProvider(this, viewModelFactory).get(MemberViewModel::class.java)

        data = intent.getStringExtra("email").toString()

        vm.getSearchUser(email = data)
        vm.searchUserLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body().let {
                    binding.email.text = it!!.createAt
                    binding.follower.text = it.follower.toString()
                    binding.following.text = it.following.toString()
                    if (it.introduce == null) {
                        binding.introduce.text = "자기소개말이 없습니다."
                    } else {
                        binding.introduce.text = it.introduce
                    }
                    binding.name.text = it.name
                    Glide.with(applicationContext).load(it.profile).into(binding.myPicture)
                }
            } else {
                Toast.makeText(applicationContext,
                    "정보를 불러올 수 없습니다.",
                    Toast.LENGTH_SHORT).show()
            }
        })

    }
}