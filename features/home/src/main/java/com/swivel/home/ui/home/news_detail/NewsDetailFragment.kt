package com.swivel.home.ui.home.news_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.swivel.core.ui.BaseFragment
import com.swivel.home.databinding.NewsDetailFragmentBinding
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class NewsDetailFragment
 *
 * detail page of news
 */
class NewsDetailFragment : BaseFragment() {
    @Inject
    lateinit var newsDetailViewModel: NewsDetailViewModel

    lateinit var newsDetailFragmentBinding: NewsDetailFragmentBinding

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        newsDetailFragmentBinding = NewsDetailFragmentBinding.inflate(inflater,container,false)
            .apply {
                lifecycleOwner = this@NewsDetailFragment
                viewModel = newsDetailViewModel
            }
        return newsDetailFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initObservers() {
        initLoader()
    }

    override fun initLoader() {
        pageNavigationId = findNavController().currentDestination?.id

        pageNavigationId?.let { pageNavId ->
            newsDetailViewModel.isLoading.observe(viewLifecycleOwner, Observer {
                progressBarHandler.toggleProgressUI(router,findNavController(),it,pageNavId)
            })
        }
    }

    override fun initViews() {
        newsDetailViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.HOME_NEWS_DETAIL))
        setupDrawer()
    }

    override fun setupDrawer() {

    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/
}