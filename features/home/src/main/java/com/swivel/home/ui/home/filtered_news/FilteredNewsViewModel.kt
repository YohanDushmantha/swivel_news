package com.swivel.home.ui.home.filtered_news

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.swivel.core.ui.BaseViewModel
import com.swivel.home.ui.home.NewsDataSourceFactory
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.home.filtered_news.router_arguments.FilteredNewsDeepLinkArguments
import com.swivel.navigation.router.Router
import com.swivel.repository.swivel_news_service_repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class FilteredNewsViewModel
 */
class FilteredNewsViewModel @Inject constructor(
    private val router : Router,
    private val context : Context,
    private val newsRepository: NewsRepository
) : BaseViewModel(){

    private var receivedDeepLinkArgs : FilteredNewsDeepLinkArguments? = null

    protected val ioScope = CoroutineScope(Dispatchers.Default)
    private val newsDataSource = NewsDataSourceFactory(
        repository = newsRepository,
        scope = ioScope
    )
    val newsList = LivePagedListBuilder(newsDataSource,pagedListConfig()).build()
    var shouldCloseFabItem : MutableLiveData<Boolean> = MutableLiveData(false)

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? FilteredNewsDeepLinkArguments)?.let {
            receivedDeepLinkArgs = it
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCleared() {
        super.onCleared()
        ioScope.coroutineContext.cancel()
    }

    fun onTapFilterButton(view: View?){
        view?.let {
            shouldCloseFabItem.postValue(true)
            Timber.i("YD -> ontap ${it.tag.toString()}")
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * DATA HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .setPageSize(5 * 2)
        .build()

    /**---------------------------------------------------------------------------------------------*
     * DATA HANDLING - END
     *----------------------------------------------------------------------------------------------*/

}