package com.swivel.home.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.swivel.home.R
import com.swivel.models.entities.News
import kotlinx.android.synthetic.main.news_row.view.*

/**
 * @author Yohan Dushmantha
 * @class HeadLineNewsAdaptor
 */
class NewsAdaptor : PagedListAdapter<News, NewsAdaptor.MyViewHolder>(
    DiffUtilCallBack()
){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_row, parent, false)
        return MyViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindPost(news: News){
            itemView.title?.text = news.title?.toString()
            itemView.description?.text = news.description?.toString()
            itemView.source?.text = news.source?.name?.toString()
            itemView.author?.text = news.author?.toString()
            Glide.with(itemView.context)
                .asBitmap()
                .placeholder(R.drawable.news_placeholder)
                .load(news.urlToImage)
                .into(itemView.bannerImg)
        }
    }
}