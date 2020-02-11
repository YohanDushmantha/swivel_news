package com.swivel.onboarding.ui.walkthrough

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swivel.onboarding.R
import kotlinx.android.synthetic.main.walkthrough_view_pager_item.view.*

/**
 * @author Yohan Dushmantha
 * @class WalkthroughViewPagerAdaptor
 */
class WalkthroughViewPagerAdaptor : RecyclerView.Adapter<WalkthroughViewPagerAdaptor.WalkthroughViewPagerViewHolder>() {
    var list : List<WalkthroughViewPagerModel> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WalkthroughViewPagerViewHolder {
        return WalkthroughViewPagerViewHolder(parent)
    }

    override fun onBindViewHolder(holder: WalkthroughViewPagerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * set walkthrough item list
     * @param list List<WalkthroughViewPageModel> walkthrough data list
     */
    fun setItem(list: List<WalkthroughViewPagerModel>){
        this.list = list
        notifyDataSetChanged()
    }

    class WalkthroughViewPagerViewHolder constructor(itemView : View) : RecyclerView.ViewHolder(itemView){
        constructor(parent : ViewGroup) : this(LayoutInflater.from(parent.context).inflate(
            R.layout.walkthrough_view_pager_item,
            parent,
            false
        ))

        /**
         * bind walkthrough model data into walkthrough adaptor view
         * @param model WalkthroughViewPageModel
         */
        fun bind(model: WalkthroughViewPagerModel){
            itemView.walkthrough_title.text = itemView.context.getText(model.titleResId)
            itemView.walkthrough_bg.setImageResource(model.slideResId)
        }

    }

}