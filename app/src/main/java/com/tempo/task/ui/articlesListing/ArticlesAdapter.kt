package com.tempo.task.ui.articlesListing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tempo.task.data.model.ArticleObject
import com.tempo.task.databinding.ArticleListingItemBinding
import com.tempo.task.utils.Extensions.loadImage

class ArticlesAdapter(private val listener: OnItemClickListener) :
    ListAdapter<ArticleObject, ArticlesAdapter.ArticlesViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val binding =
            ArticleListingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticlesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ArticlesViewHolder(private val binding: ArticleListingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(article: ArticleObject) {
            binding.apply {
                tvDesc.text = article.description
                ivArticleImage.loadImage(article.urlToImage)
                tvSource.text = article.source.name
                binding.root.setOnClickListener {
                    listener.onItemClick(article)
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(article: ArticleObject)
    }

    class DiffCallBack : DiffUtil.ItemCallback<ArticleObject>() {
        override fun areItemsTheSame(oldItem: ArticleObject, newItem: ArticleObject): Boolean {
            return oldItem.urlToImage == newItem.urlToImage

        }

        override fun areContentsTheSame(oldItem: ArticleObject, newItem: ArticleObject): Boolean {
            return oldItem == newItem
        }
    }
}