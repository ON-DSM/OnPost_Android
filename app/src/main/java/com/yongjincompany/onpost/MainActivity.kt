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

        binding.popularity.setOnClickListener {
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
        }



        binding.latest.setOnClickListener {
            vm.getPost(sort = "NEW", page = 1)
            vm.modo.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    response.body()!!.let {
                        myAdapter.setData(it)
                    }
                } else {

                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        initview()
    }

    private fun setupRecylerview() {
        val recylerView = binding.postList
        recylerView.adapter = myAdapter
        recylerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initview() {
        binding.popularity.setOnClickListener {
            binding.popularity.setTextColor(Color.parseColor("#303f9f"))
            binding.popularity.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22F)
            binding.latest.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18F)
            binding.latest.setTextColor(Color.parseColor("#A3A3A3"))
        }
        binding.latest.setOnClickListener {
            binding.latest.setTextColor(Color.parseColor("#303f9f"))
            binding.latest.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22F)
            binding.popularity.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18F)
            binding.popularity.setTextColor(Color.parseColor("#A3A3A3"))
        }
    }
}