package com.swivel.home.ui.home

import androidx.paging.PageKeyedDataSource
import com.swivel.home.ui.home.filtered_news.enums.NewsListFilterType
import com.swivel.models.base.DataSource
import com.swivel.models.entities.News
import com.swivel.repository.swivel_news_service_repositories.NewsRepository
import kotlinx.coroutines.*
import timber.log.Timber


/**
 * @author Yohan Dushmantha
 * @class NewsDataSource
 *
 * news data source contains page related data and retrieving logics
 */
class NewsDataSource constructor(
    private val repository: NewsRepository,
    private val scope: CoroutineScope,
    private val newsListFilterType: NewsListFilterType,
    private val newsFilterConfig: NewsFilterConfig? = null
): PageKeyedDataSource<Int, News>() {

    private var supervisorJob = SupervisorJob()

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {

    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, News>) {
        executeQuery(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        val page = params.key
        executeQuery(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()
    }

    /**
     * execute data fetching query
     * @param page requested page
     * @param perPage item count for page
     * @param callback callback function for calling after fetching data
     */
    private fun executeQuery(page: Int, perPage: Int, callback:(List<News>) -> Unit) {
        scope.launch(getJobErrorHandler() + supervisorJob) {
            delay(200)
            //change fetch method according to filter type
            when(newsListFilterType){
                NewsListFilterType.HEADLINES -> {
                    callback(repository.fetchHeadLineNews(DataSource.REMOTE,page, perPage))
                }
                NewsListFilterType.FILTERED_NEWS -> {
                    newsFilterConfig?.let {
                        callback(repository.fetchFilteredNews(DataSource.REMOTE,newsFilterConfig.filteredName,page, perPage))
                    }
                }
            }
        }
    }

    /**
     * triggered when error happend during loading data
     */
    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Timber.e(e)
    }
}