package com.swivel.home.ui.home.news_detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.entities.News
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.home.news_detail.router_arguments.NewsDetailDeepLinkArguments
import com.swivel.navigation.router.Router
import javax.inject.Inject

class NewsDetailViewModel@Inject constructor(
    private val router : Router,
    private val context : Context
) : BaseViewModel(){

    private var receivedDeepLinkArgs : NewsDetailDeepLinkArguments? = null
    var news : MutableLiveData<News> = MutableLiveData()

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? NewsDetailDeepLinkArguments)?.let {
            receivedDeepLinkArgs = it
            news.postValue(it.news)
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *----------------------------------------------------------------------------------------------*/



    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/

}