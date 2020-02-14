package com.swivel.home.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.swivel.home.R
import com.swivel.models.entities.News
import kotlinx.android.synthetic.main.news_row.view.*

/**
 * @author Yohan Dushmantha
 * @class HeadLineNewsAdaptor
 */
class HeadLineNewsAdaptor : PagedListAdapter<News,HeadLineNewsAdaptor.MyViewHolder>(DiffUtilCallBack()){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeadLineNewsAdaptor.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPost(it) }
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var bannerImg = itemView.bannerImg
        var title = itemView.title
        var description = itemView.description
        var source = itemView.source
        var author = itemView.author

        fun bindPost(news: News){
            title?.text = news.title?.toString()
            description?.text = news.description?.toString()
            source?.text = news.source?.name?.toString()
            author?.text = news.author?.toString()
        }
    }
}