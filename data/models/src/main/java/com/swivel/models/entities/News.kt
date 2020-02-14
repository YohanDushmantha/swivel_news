package com.swivel.models.entities

/**
 * @author Yohan Dushmantha
 * @class News
 *
 * contains news data
 */
data class News constructor(
    val source : Source? = null,
    val author : String? = null,
    val title : String? = null,
    val description : String? = null,
    val url : String? = null,
    val urlToImage : String? = null,
    val publishedAt : String? = null,
    val content : String? = null
)