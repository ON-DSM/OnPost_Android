package com.yongjincompany.onpost.ui.post

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.yongjincompany.onpost.databinding.ActivityMainBinding
import com.yongjincompany.onpost.databinding.ActivityPostDetailBinding
import com.yongjincompany.onpost.repository.PostRepository
import com.yongjincompany.onpost.ui.post.adapter.CommentListAdapter
import com.yongjincompany.onpost.ui.post.adapter.PostListAdapter
import com.yongjincompany.onpost.viewmodel.post.PostViewModel
import com.yongjincompany.onpost.viewmodel.post.PostViewModelFactory

class PostDetailActivity : AppCompatActivity() {

    private lateinit var vm: PostViewModel
    private lateinit var binding: ActivityPostDetailBinding
    var data: Int = 0

    private val myAdapter by lazy {
        CommentListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecylerview()

        val repository = PostRepository()
        val viewModelFactory = PostViewModelFactory(repository)
        vm = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)

        data = intent.getIntExtra("postid", data)

        vm.getShowPost(id = data)
        vm.showPostLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body().let {

                        binding.content.text = it!!.content
                        binding.tag.text = it.tags
                        binding.title.text = it.title
                        binding.date.text = it.createAt
                        binding.writer.text = it.writer.name
                        binding.like.text = it.like.toString()
                        Glide.with(applicationContext).load(it.writer.profile).into(binding.ivWriter)
                        binding.writerName.text = it.writer.name
                        binding.writerContent.text = it.writer.introduce
                        myAdapter.setData(it.comments)
                    if(it.tags == null) {
                        binding.tag.text = "태그 정보가 없습니다"
                    }

                }
            } else {
                Toast.makeText(applicationContext,
                    "정보를 불러올 수 없습니다.",
                    Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun setupRecylerview() {
        val recylerView = binding.commentList
        recylerView.adapter = myAdapter
        recylerView.layoutManager = LinearLayoutManager(this)
    }
}