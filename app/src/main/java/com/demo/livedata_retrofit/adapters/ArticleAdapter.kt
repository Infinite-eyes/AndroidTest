package com.demo.livedata_retrofit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.androidtest.databinding.LivedataRetrofitItemBinding
import com.demo.livedata_retrofit.model.Data

/**
 * @author chenweiming
 * @version 1.0
 * @mail cwm930215@gmail.com
 * @since 2020/7/22 7:18 PM
 **/
class ArticleAdapter : ListAdapter<Data, RecyclerView.ViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleViewHolder(
            LivedataRetrofitItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)
        (holder as ArticleViewHolder).bind(data)
    }


    class ArticleViewHolder(
        private val binding: LivedataRetrofitItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Data) {
            binding.apply {
                data = item
                executePendingBindings()
            }
        }
    }

    //todo

    private class ArticleDiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }
}

