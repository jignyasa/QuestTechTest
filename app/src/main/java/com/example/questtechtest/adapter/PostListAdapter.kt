package com.example.questtechtest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.questtechtest.R
import com.example.questtechtest.databinding.ItemPostListBinding
import com.example.questtechtest.model.PostList
import java.util.*

class PostListAdapter(val listener: PostListAdapter.onItemClickListener) :
    RecyclerView.Adapter<PostListViewHolder>() {
    var list = ArrayList<PostList>()
    var selectedList = ArrayList<PostList>()
    private lateinit var binding: ItemPostListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post_list, parent, false)
        return PostListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        var data = list.get(position)
        holder.tvName.text = data.title
        Timer().schedule(object : TimerTask() {
            override fun run() {
                holder.chkPost.isEnabled = false
            }

        }, (position + 1) * (60 * 1000L))
        Log.e("timer", "".plus((position + 1) * (60 * 1000L)))
        holder.chkPost.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                data.isSelected = isChecked
                selectedList.add(data)
            }
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(data)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun addData(postList: ArrayList<PostList>) {
        list = postList
        notifyDataSetChanged()
    }

    fun getSelectedPostList(): ArrayList<PostList> {
        return selectedList
    }

    interface onItemClickListener {
        fun onItemClick(data: PostList)
    }
}

class PostListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<AppCompatTextView>(R.id.tvName)
    val chkPost = view.findViewById<AppCompatCheckBox>(R.id.checkPost)
}
