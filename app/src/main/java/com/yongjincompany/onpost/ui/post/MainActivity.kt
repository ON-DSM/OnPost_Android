package com.yongjincompany.onpost.ui.post

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.bumptech.glide.Glide
import com.yongjincompany.onpost.databinding.ActivityMainBinding
import com.yongjincompany.onpost.repository.PostRepository
import com.yongjincompany.onpost.ui.post.adapter.PostListAdapter
import com.yongjincompany.onpost.viewmodel.post.PostViewModel
import com.yongjincompany.onpost.viewmodel.post.PostViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var vm: PostViewModel
    private lateinit var binding: ActivityMainBinding

    private val myAdapter by lazy {
        PostListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecylerview()

        val repository = PostRepository()
        val viewModelFactory = PostViewModelFactory(repository)
        vm = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)

        fetchLike()

        val animator = binding.postList.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
        binding.btnPopularity.setOnClickListener {
            fetchLike()
        }

        binding.btnLatest.setOnClickListener {
            fetchLatest()
        }

        binding.btnComment.setOnClickListener {
            fetchComment()
        }

        binding.searchBtn.setOnClickListener {
            val intent = Intent(applicationContext, SearchPostActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setupRecylerview() {
        val recylerView = binding.postList
        recylerView.adapter = myAdapter
        recylerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchLike() {
        vm.getPost(sort = "LIKE", page = 1)
        vm.sortPostLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body().let {
                    if (it != null) {
                        myAdapter.setData(it)
                    }

                }
            } else {

            }
        })

        vm.getTopThreePost(sort = "LIKE")
        vm.topThreePostLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()!!.forEach {
                    when (it.rank) {
                        1 -> {
                            binding.winnerName.text = it.title
                            binding.winnerDetail.text = it.like.toString()
                            Glide.with(applicationContext).load(it.profileImage)
                                .into(binding.ivWinner)
                        }
                        2 -> {
                            binding.secondName.text = it.title
                            binding.secondDetail.text = it.like.toString()
                            Glide.with(applicationContext).load(it.profileImage)
                                .into(binding.ivSecond)
                        }
                        3 -> {
                            binding.thirdName.text = it.title
                            binding.thirdDetail.text = it.like.toString()
                            Glide.with(applicationContext).load(it.profileImage)
                                .into(binding.ivThird)
                        }
                        else -> {

                        }
                    }
                }
            }
        })
    }

    private fun fetchComment() {
        vm.getPost(sort = "COMMENTS", page = 1)
        vm.sortPostLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()!!.let {
                    myAdapter.setData(it)
                }
            } else {
                Toast.makeText(applicationContext,
                    "리스트를 불러오지 못했습니다",
                    Toast.LENGTH_SHORT).show()
            }
        })

        vm.getTopThreePost(sort = "COMMENTS")
        vm.topThreePostLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()!!.forEach {
                    when (it.rank) {
                        1 -> {
                            binding.winnerName.text = it.title
                            binding.winnerDetail.text = it.comments.toString()
                            Glide.with(applicationContext).load(it.profileImage)
                                .into(binding.ivWinner)
                        }
                        2 -> {
                            binding.secondName.text = it.title
                            binding.secondDetail.text = it.comments.toString()
                            Glide.with(applicationContext).load(it.profileImage)
                                .into(binding.ivSecond)
                        }
                        3 -> {
                            binding.thirdName.text = it.title
                            binding.thirdDetail.text = it.comments.toString()
                            Glide.with(applicationContext).load(it.profileImage)
                                .into(binding.ivThird)
                        }
                        else -> {

                        }
                    }
                }
            }
        })
    }

    private fun fetchLatest() {
        vm.getPost(sort = "NEW", page = 1)
        vm.sortPostLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()!!.let {
                    myAdapter.setData(it)
                }
            } else {
                Toast.makeText(applicationContext,
                    "리스트를 불러오지 못했습니다",
                    Toast.LENGTH_SHORT).show()
            }
        })

        vm.getTopThreePost(sort = "NEW")
        vm.topThreePostLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()!!.forEach {
                    when (it.rank) {
                        1 -> {
                            binding.winnerName.text = it.title
                            binding.winnerDetail.text = it.createAt.toString()
                            Glide.with(applicationContext).load(it.profileImage)
                                .into(binding.ivWinner)
                        }
                        2 -> {
                            binding.secondName.text = it.title
                            binding.secondDetail.text = it.createAt.toString()
                            Glide.with(applicationContext).load(it.profileImage)
                                .into(binding.ivSecond)
                        }
                        3 -> {
                            binding.thirdName.text = it.title
                            binding.thirdDetail.text = it.createAt.toString()
                            Glide.with(applicationContext).load(it.profileImage)
                                .into(binding.ivThird)
                        }
                        else -> {

                        }
                    }
                }
            }
        })
    }
}