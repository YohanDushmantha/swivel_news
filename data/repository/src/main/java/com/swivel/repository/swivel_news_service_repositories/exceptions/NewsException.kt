package com.swivel.repository.swivel_news_service_repositories.exceptions

/**
 * @author Yohan Dushmantha
 * @class NewsException
 *
 * NewsException should be throw when error occurred during retrieving news from server
 */
class NewsException constructor(val errorMessage : String? = null) : Exception() {

    override val message: String?
        get() = errorMessage ?: "News Retrieving Failed Exception"

}