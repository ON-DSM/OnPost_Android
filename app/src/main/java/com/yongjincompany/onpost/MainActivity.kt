package com.yongjincompany.onpost

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yongjincompany.onpost.databinding.ActivityMainBinding
import com.yongjincompany.onpost.repository.PostRepository
import com.yongjincompany.onpost.ui.adapter.PostListAdapter
import com.yongjincompany.onpost.viewmodel.PostViewModel
import com.yongjincompany.onpost.viewmodel.PostViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var vm: PostViewModel

    private lateinit var binding: ActivityMainBinding

    private val myAdapter by lazy {
        PostListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecylerview()


        val repository = PostRepository()
        val viewModelFactory = PostViewModelFactory(repository)
        vm = ViewModelProvider(this, viewModelFactory).get(PostViewModel::class.java)


        vm.getPost(sort = "LIKE", page = 1)
        vm.modo.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body().let {
                    if (it != null) {
                        myAdapter.setData(it)
                    }
                }
            } else {
                Log.d("error", response.errorBody().toString())
                Toast.makeText(applicationContext,
                    response.errorBody().toString(),
                    Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnPopularity.setOnClickListener {
            vm.getPost(sort = "LIKE", page = 1)
            vm.modo.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    response.body().let {
                        if (it != null) {
                            myAdapter.setData(it)
                        }
                    }
                }
                else {
                    Log.d("error", response.errorBody().toString())
                    Toast.makeText(applicationContext,
                        "리스트를 불러오지 못했습니다",
                        Toast.LENGTH_SHORT).show()
                }
            })
        }



        binding.btnLatest.setOnClickListener {
            vm.getPost(sort = "NEW", page = 1)
            vm.modo.observe(this, Observer { response ->
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
        }

        binding.btnComment.setOnClickListener {
            vm.getPost(sort = "COMMENTS", page = 1)
            vm.modo.observe(this, Observer { response ->
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
        }
    }

    private fun setupRecylerview() {
        val recylerView = binding.postList
        recylerView.adapter = myAdapter
        recylerView.layoutManager = LinearLayoutManager(this)
    }
}