package com.swivel.home.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.swivel.home.ui.home.enums.HomeConstants
import com.swivel.models.base.DataSource
import com.swivel.models.entities.News
import com.swivel.repository.swivel_news_service_repositories.NewsRepository
import kotlinx.coroutines.*
import timber.log.Timber


/**
 * @author Yohan Dushmantha
 * @class NewsDataSource
 */
class NewsDataSource constructor(
    private val repository: NewsRepository,
    private val scope: CoroutineScope
): PageKeyedDataSource<Int, News>() {

    // FOR DATA ---
    private var supervisorJob = SupervisorJob()
    //...

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {

    }
    // OVERRIDE ---
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, News>) {
        // ...
        executeQuery(1, params.requestedLoadSize) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        val page = params.key
        // ...
        executeQuery(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()   // Cancel possible running job to only keep last result searched by user
    }

    // UTILS ---
    private fun executeQuery(page: Int, perPage: Int, callback:(List<News>) -> Unit) {
        // ...
        scope.launch(getJobErrorHandler() + supervisorJob) {
            delay(200) // To handle user typing case
            val newsList = repository.fetchHeadLineNews(DataSource.REMOTE,page, perPage)
            // ...
            callback(newsList)
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
//        Timber.e(NewsDataSource::class.java.simpleName, "An error happened: $e")
//        networkState.postValue(NetworkState.FAILED)
    }

    // ...
}