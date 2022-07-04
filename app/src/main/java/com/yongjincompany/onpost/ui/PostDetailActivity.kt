package com.yongjincompany.onpost.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yongjincompany.onpost.databinding.ActivityMainBinding
import com.yongjincompany.onpost.databinding.ActivityPostDetailBinding
import com.yongjincompany.onpost.repository.PostRepository
import com.yongjincompany.onpost.viewmodel.PostViewModel
import com.yongjincompany.onpost.viewmodel.PostViewModelFactory

class PostDetailActivity : AppCompatActivity() {

    private lateinit var vm: PostViewModel
    private lateinit var binding: ActivityPostDetailBinding
    var data: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = PostRepository()
        val viewModelFactory = PostViewModelFactory(repository)
        vm = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)

        data = intent.getIntExtra("postid", data)

        vm.getShowPost(id = data)
        vm.showPostLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body().let {
                    if (it != null) {
                        binding.content.text = it.content
                        binding.tag.text = it.tags
                        binding.title.text = it.title
                        binding.date.text = it.createAt
                        binding.writer.text = it.writer.name
                        binding.like.text = it.like.toString()
                    }
                }
            } else {
                Toast.makeText(applicationContext,
                    "정보를 불러올 수 없습니다.",
                    Toast.LENGTH_SHORT).show()
            }
        })
    }
}