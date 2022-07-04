package com.yongjincompany.onpost.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yongjincompany.onpost.R
import com.yongjincompany.onpost.databinding.ItemPostBinding
import com.yongjincompany.onpost.remote.response.SearchPostResponse
import com.yongjincompany.onpost.ui.PostDetailActivity
import kotlin.properties.Delegates

class SearchPostListAdapter : RecyclerView.Adapter<SearchPostListAdapter.SearchPostDataViewHolder>() {
    inner class SearchPostDataViewHolder(val itemPostBinding: ItemPostBinding) :
        RecyclerView.ViewHolder(itemPostBinding.root)

    private lateinit var context: Context
    private var myList = emptyList<SearchPostResponse>()
    private var postId by Delegates.notNull<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchPostDataViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_post,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: SearchPostDataViewHolder, position: Int) {
        context = holder.itemPostBinding.userProfileImage.context
        holder.itemPostBinding.title.text = myList[position].title
        holder.itemPostBinding.name.text = myList[position].writer.name
        holder.itemPostBinding.likeText.text = myList[position].like.toString()
        holder.itemPostBinding.date.text = myList[position].createAt
        holder.itemPostBinding.tag.text = myList[position].tags
        Glide.with(context).load(myList[position].profileImage).into(holder.itemPostBinding.ivPost)
        Glide.with(context).load(myList[position].writer.profile)
            .into(holder.itemPostBinding.userProfileImage)
        holder.itemView.setOnClickListener {
            postId = myList[position].id
            val intent = Intent(context, PostDetailActivity::class.java)

            intent.putExtra("postid", postId)
            context.startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<SearchPostResponse>) {
        myList = newList
        notifyDataSetChanged()
    }
}