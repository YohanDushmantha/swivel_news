package com.swivel.home.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.swivel.home.ui.home.filtered_news.enums.NewsListFilterType
import com.swivel.models.entities.News
import com.swivel.repository.swivel_news_service_repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope


/**
 * @author Yohan Dushmantha
 * @class NewsDataSourceFactory
 *
 * this class provide data source item when required
 */
class NewsDataSourceFactory(
    private val repository: NewsRepository,
    private val scope: CoroutineScope,
    private val newsListFilterType: NewsListFilterType,
    private var newsFilterConfig: NewsFilterConfig? = null
) : DataSource.Factory<Int, News>() {

    val source = MutableLiveData<NewsDataSource>()

    /**
     * setNewsFilterConfig method should be called when need to change the filter configuration
     *
     * @param filterConfig contains data for dynamic data loading from remotely
     */
    fun setNewsFilterConfig(filterConfig : NewsFilterConfig?){
        this.newsFilterConfig = filterConfig
    }

    override fun create(): DataSource<Int, News> {
        val source = NewsDataSource(repository, scope, newsListFilterType,newsFilterConfig)
        this.source.postValue(source)
        return source
    }
}