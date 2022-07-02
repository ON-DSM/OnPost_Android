package com.yongjincompany.onpost.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.yongjincompany.onpost.R
import com.yongjincompany.onpost.databinding.ItemPostBinding
import com.yongjincompany.onpost.remote.response.SortPostResponse

class PostListAdapter :
    RecyclerView.Adapter<PostListAdapter.PostDataViewHolder>() {
    inner class PostDataViewHolder(val itemPostBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemPostBinding.root)

    private var myList = emptyList<SortPostResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostDataViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_post,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: PostDataViewHolder, position: Int) {
        holder.itemPostBinding.title.text = myList[position].title
        holder.itemPostBinding.name.text = myList[position].writer.name
        holder.itemPostBinding.likeText.text = myList[position].like.toString()
        holder.itemPostBinding.date.text = myList[position].createAt
        holder.itemPostBinding.tag.text = myList[position].tags
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<SortPostResponse>) {
        myList = newList
        notifyDataSetChanged()
    }
}