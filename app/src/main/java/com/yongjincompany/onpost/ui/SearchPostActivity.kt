package com.yongjincompany.onpost.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yongjincompany.onpost.databinding.ActivitySearchPostBinding
import com.yongjincompany.onpost.repository.PostRepository
import com.yongjincompany.onpost.ui.adapter.SearchPostListAdapter
import com.yongjincompany.onpost.viewmodel.PostViewModel
import com.yongjincompany.onpost.viewmodel.PostViewModelFactory


class SearchPostActivity : AppCompatActivity() {

    private lateinit var vm: PostViewModel
    private lateinit var binding: ActivitySearchPostBinding

    private val myAdapter by lazy {
        SearchPostListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySearchPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecylerview()

        val repository = PostRepository()
        val viewModelFactory = PostViewModelFactory(repository)
        vm = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)

        val data = binding.searchEt.text.toString()

        binding.searchBtn.setOnClickListener {
            vm.getSearchPost(param = data)
            vm.searchPostLiveData.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            myAdapter.setData(it)
                        }

                    }
                } else {

                }
            })
        }

    }

    private fun setupRecylerview() {
        val recylerView = binding.searchPostList
        recylerView.adapter = myAdapter
        recylerView.layoutManager = LinearLayoutManager(this)
    }
}