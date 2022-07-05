package com.yongjincompany.onpost.ui.post.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yongjincompany.onpost.R
import com.yongjincompany.onpost.databinding.ItemCommentBinding
import com.yongjincompany.onpost.remote.response.post.ReadPostResponse
import com.yongjincompany.onpost.ui.member.UserInfoActivity
import kotlin.properties.Delegates

class CommentListAdapter : RecyclerView.Adapter<CommentListAdapter.CommentDataViewHolder>() {
    inner class CommentDataViewHolder(val itemCommentBinding: ItemCommentBinding) :
        RecyclerView.ViewHolder(itemCommentBinding.root)

    private lateinit var context: Context
    private var myList = emptyList<ReadPostResponse.Comment>()
    private var email by Delegates.notNull<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CommentDataViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_comment,
            parent,
            false
        )
    )

    override fun getItemCount() = myList.size

    override fun onBindViewHolder(holder: CommentDataViewHolder, position: Int) {
        context = holder.itemCommentBinding.name.context
        holder.itemCommentBinding.name.text = myList[position].writer.name
        holder.itemCommentBinding.comment.text = myList[position].content
        Glide.with(context).load(myList[position].writer.profile).into(holder.itemCommentBinding.ivUserProfile)
        holder.itemView.setOnClickListener {
            email = myList[position].writer.email
            val intent = Intent(context, UserInfoActivity::class.java)

            intent.putExtra("email", email)
            context.startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<ReadPostResponse.Comment>) {
        myList = newList
        notifyDataSetChanged()
    }
}