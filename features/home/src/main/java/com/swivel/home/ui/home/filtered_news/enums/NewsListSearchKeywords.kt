package com.swivel.home.ui.home.filtered_news.enums

import com.swivel.home.R

/**
 * @author Yohan Dushmantha
 * @class NewsListSearchKeywords
 *
 * news list search key words
 */
enum class NewsListSearchKeywords(val keyword : String, val label : String, val image : Int) {
    APPLE("apple", "APPLE" ,R.drawable.ic_apple),
    BITCOIN("bitcoin", "BITCOIN",R.drawable.ic_bit_coin),
    EARTHQUAKE("earthquake","EARTHQUAKE",R.drawable.ic_earth_quake),
    ANIMAL("animal","ANIMAL",R.drawable.ic_animal)
}