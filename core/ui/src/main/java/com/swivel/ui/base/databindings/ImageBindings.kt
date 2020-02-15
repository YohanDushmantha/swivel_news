package com.swivel.ui.base.databindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.swivel.ui.R

/**
 * binding adaptor for binding network images to image views
 * @param imageView imgage view
 * @param network url
 */
@BindingAdapter("app:imageResource")
fun setImageViewResource(imageView: ImageView, url : String?) {
    Glide.with(imageView.context)
        .asBitmap()
        .placeholder(R.drawable.news_placeholder)
        .load(url)
        .into(imageView)
}