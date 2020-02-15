package com.swivel.home.ui.home.filtered_news

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.swivel.core.ui.BaseViewModel
import com.swivel.home.ui.home.NewsDataSourceFactory
import com.swivel.home.ui.home.NewsFilterConfig
import com.swivel.home.ui.home.filtered_news.enums.NewsListFilterType
import com.swivel.home.ui.home.filtered_news.enums.NewsListSearchKeywords
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.home.filtered_news.router_arguments.FilteredNewsDeepLinkArguments
import com.swivel.navigation.router.Router
import com.swivel.repository.swivel_news_service_repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
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
    private val ioScope = CoroutineScope(Dispatchers.Default)
    var shouldCloseFabItem : MutableLiveData<Boolean> = MutableLiveData(false)
    val selectedFilter : MutableLiveData<NewsListSearchKeywords> = MutableLiveData()

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? FilteredNewsDeepLinkArguments)?.let {
            receivedDeepLinkArgs = it
        }
        selectedFilter.postValue(NewsListSearchKeywords.APPLE)
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

    /**
     * triggered when adding some filter
     */
    fun onTapFilterButton(view: View?){
        view?.let {
            shouldCloseFabItem.postValue(true)
            selectedFilter.postValue(getSelectedFilter(it.tag.toString()))
            newsDataSourceFactory.setNewsFilterConfig(NewsFilterConfig(it.tag.toString()))
            newsDataSourceFactory.source.value?.invalidate()
        }
    }

    private fun getSelectedFilter (keyword : String) : NewsListSearchKeywords{
        return when(keyword){
            NewsListSearchKeywords.APPLE.keyword -> NewsListSearchKeywords.APPLE
            NewsListSearchKeywords.BITCOIN.keyword -> NewsListSearchKeywords.BITCOIN
            NewsListSearchKeywords.EARTHQUAKE.keyword -> NewsListSearchKeywords.EARTHQUAKE
            NewsListSearchKeywords.ANIMAL.keyword -> NewsListSearchKeywords.ANIMAL
            else -> NewsListSearchKeywords.APPLE
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * NEWS LIST DATA HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * prepare data source for news list adaptor
     */
    private val newsDataSourceFactory =  NewsDataSourceFactory(
        repository = newsRepository,
        scope = ioScope,
        newsListFilterType = NewsListFilterType.FILTERED_NEWS,
        newsFilterConfig = NewsFilterConfig(NewsListSearchKeywords.APPLE.keyword)
    )

    /**
     * news page list
     */
    val newsList = LivePagedListBuilder(
        newsDataSourceFactory,
        pagedListConfig()
    ).build()

    /**
     * page list config object for generating live news list
     */
    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .setPageSize(5 * 2)
        .build()

    /**---------------------------------------------------------------------------------------------*
     * NEWS LIST DATA HANDLING - END
     *----------------------------------------------------------------------------------------------*/

}