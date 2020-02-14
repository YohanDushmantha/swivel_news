package com.swivel.home.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.swivel.models.entities.News
import com.swivel.repository.swivel_news_service_repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope


/**
 * @author Yohan Dushmantha
 * @class NewsDataSourceFactory
 *
 */
class NewsDataSourceFactory (private val repository: NewsRepository,
                             private val scope: CoroutineScope
): DataSource.Factory<Int, News>() {

    val source = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, News> {
        val source = NewsDataSource(repository, scope)
        this.source.postValue(source)
        return source
    }

    // ...
}