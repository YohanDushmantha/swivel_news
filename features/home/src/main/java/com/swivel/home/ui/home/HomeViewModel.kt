package com.swivel.home.ui.home

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.home.home.router_arguments.HomeDeepLinkArguments
import com.swivel.navigation.router.Router
import com.swivel.repository.swivel_news_service_repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class HomeViewModel
 */
class HomeViewModel @Inject constructor(
    private val router: Router,
    private val context: Context,
    private val newsRepository: NewsRepository
) : BaseViewModel(){

    val isDrawerOpen : MutableLiveData<Boolean> = MutableLiveData()
    val receivedViewArguments : MutableLiveData<HomeDeepLinkArguments> = MutableLiveData()

    protected val ioScope = CoroutineScope(Dispatchers.Default)
    private val newsDataSource = NewsDataSourceFactory(repository = newsRepository,scope = ioScope)
    val newsList = LivePagedListBuilder(newsDataSource,pagedListConfig()).build()

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        deepLinkArguments?.let {
            receivedViewArguments.postValue(it as? HomeDeepLinkArguments)
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    override fun onCleared() {
        super.onCleared()
        ioScope.coroutineContext.cancel()
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/


    /**---------------------------------------------------------------------------------------------*
     * DATA HANDLING - START
     *----------------------------------------------------------------------------------------------*/

//    fun fetchNews() {
//        if (newsDataSource.getQuery() == search) return
//        newsDataSource.updateQuery(search, sharedPrefsManager.getFilterWhenSearchingUsers().value)
//    }

    // ...

    // UTILS ---

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .setPageSize(5 * 2)
        .build()

    /**---------------------------------------------------------------------------------------------*
     * DATA HANDLING - END
     *----------------------------------------------------------------------------------------------*/

}