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
 *
 * news adaptor used to bind data into recycler view items and binding events of recycler view item
 */
class NewsAdaptor constructor(
    private val viewMoreCallback : (news : News, view : View?) -> Unit,
    private val externalLink : (link : String?,view : View?) -> Unit
) : PagedListAdapter<News, NewsAdaptor.MyViewHolder>(
    DiffUtilCallBack()
) {
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
        getItem(position)?.let { holder.bindPost(it,viewMoreCallback,externalLink) }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /**
         * bind data into row
         */
        fun bindPost(
            news: News,
            viewMoreCallback : (news : News, view : View?) -> Unit,
            externalLink : (link : String, view : View?) -> Unit
        ) {
            itemView.title?.text = news.title
            itemView.description?.text = news.description
            itemView.source?.text = news.source?.name
            itemView.author?.text = news.author
            Glide.with(itemView.context)
                .asBitmap()
                .placeholder(R.drawable.news_placeholder)
                .load(news.urlToImage)
                .into(itemView.bannerImg)

            itemView.learnMoreBtn?.setOnClickListener {
                viewMoreCallback(news,it)
            }

            itemView.externalLink?.setOnClickListener {
                news.url?.let { text ->
                    externalLink(text,it)
                }
            }
        }
    }
}