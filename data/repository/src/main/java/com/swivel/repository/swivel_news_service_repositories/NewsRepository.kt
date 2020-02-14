package com.swivel.repository.swivel_news_service_repositories

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.swivel.models.base.DataSource
import com.swivel.models.dto.driver_authentication_api.response.NewsResponse
import com.swivel.models.entities.News
import com.swivel.remote.news_service.api.news_api.NewsRemoteApi
import com.swivel.repository.R
import com.swivel.repository.core.BaseRepository
import com.swivel.repository.exception.DataSourceNotHandledException
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class NewsRepository
 *
 * provide capability of fetching news data from data sources
 */
class NewsRepository @Inject constructor(
    private val context: Context,
    private val newsRemoteApi: NewsRemoteApi
) : BaseRepository(){

    /**
     * fetch head line news list
     * @param dataSource datasource to fetch data
     * @param pageNumber requested page
     * @param defaultPageSize default size of page
     * @return MutableList<News>
     */
    suspend fun fetchHeadLineNews(
        dataSource: DataSource? = DataSource.REMOTE,
        pageNumber :  Int? = 0,
        defaultPageSize : Int? = 10
    ) : MutableList<News>{
        when(dataSource){

            // handle remote datasource related data reqeusts
            DataSource.REMOTE -> {
                return safeApiCall(
                    call = {newsRemoteApi.fetchHeadLineNews(pageNumber,defaultPageSize).await()},
                    errorMessage = context.getString(R.string.news_repo_news_headline_retrieving_error)
                )?.articles?.toMutableList()!!
            }

            else -> throw DataSourceNotHandledException()
        }

    }

    /**
     * fetch filtered news list
     * @param dataSource datasource to fetch data
     * @param query value to search news
     * @param pageNumber requested page
     * @param defaultPageSize default size of page
     * @return MutableList<News>
     */
    suspend fun fetchFilteredNews(
        dataSource: DataSource? = DataSource.REMOTE,
        query : String? = null,
        pageNumber :  Int? = 0,
        defaultPageSize : Int? = 10
    ) : MutableList<News>{
        when(dataSource){

            // handle remote datasource related data reqeusts
            DataSource.REMOTE -> {
                return safeApiCall(
                    call = {newsRemoteApi.fetchFilteredNews(query,pageNumber,defaultPageSize).await()},
                    errorMessage = context.getString(R.string.news_repo_filtered_news_retrieving_error)
                )?.articles?.toMutableList()!!
            }

            else -> throw DataSourceNotHandledException()
        }

    }

}