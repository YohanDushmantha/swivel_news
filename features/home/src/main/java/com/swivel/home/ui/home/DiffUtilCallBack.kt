package com.swivel.home.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.swivel.models.entities.News

/**
 * @author Yohan Dushmantha
 * @class DiffUtilCallBack
 *
 * provide item available status
 */
class DiffUtilCallBack  : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.publishedAt.equals(newItem.publishedAt)
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title.equals(newItem.title)
                    && oldItem.publishedAt.equals(newItem.publishedAt)
    }

}