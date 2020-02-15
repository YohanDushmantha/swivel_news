package com.swivel.home.ui.home.headline_news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.swivel.core.ui.BaseFragment
import com.swivel.home.R
import com.swivel.home.databinding.HomeFragmentBinding
import com.swivel.home.ui.home.NewsAdaptor
import com.swivel.models.entities.News
import com.swivel.models.features.home.news_detail.router_arguments.NewsDetailDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.models.libs.navigation.enums.DrawerConfigSettings
import com.swivel.ui.base.helpers.animation_handler.bounce_handler.BounceAnimationHandler
import com.swivel.ui.base.helpers.back_handler.BackHandler
import kotlinx.android.synthetic.main.home_content_layout.*
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Yohan Dushmantha
 * @class HomeFragment
 *
 * show headline news list inside the home page
 */
class HomeFragment : BaseFragment(){

    @Inject lateinit var homeViewModel: HomeViewModel
    @Inject lateinit var backHandler: BackHandler
    @Inject lateinit var bounceHandler: BounceAnimationHandler

    private lateinit var homeFragmentBinding : HomeFragmentBinding

    /**
     * show news detail page
     */
    private val onTapNewsViewMoreCallback : ((news : News?, View : View?) -> Unit) = { news , view ->
        bounceHandler.animate(view)
        news?.let {
            router.route(findNavController(),DEEP_LINK.HOME_NEWS_DETAIL,null,
                NewsDetailDeepLinkArguments(news)
            )
        }
    }

    /**
     * open provided external link using device browser
     */
    private val onTapExternalNewsLink : ((url : String?, view : View?) -> Unit) = { url, view ->
        bounceHandler.animate(view)
        url?.let {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }

    private val newsAdaptor : NewsAdaptor = NewsAdaptor(onTapNewsViewMoreCallback,onTapExternalNewsLink)

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeFragmentBinding = HomeFragmentBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = this@HomeFragment
            viewModel = homeViewModel
        }
        return  homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
        initBackListener()
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
            homeViewModel.isLoading.observe(this, Observer {
                it?.let {
                    progressBarHandler.toggleProgressUI(router,findNavController(),it,pageNavId)
                }
            })
        }
        homeViewModel.isLoading.postValue(true)
    }

    override fun initViews() {
        setupDrawer()
        setupBottomNavigationBar()
        initializeNewsListUpdateListener()
        setupNewsList()
        homeViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.HOME_MAIN))
    }

    /**
     * update news list when receiving new values to newsList
     */
    private fun initializeNewsListUpdateListener(){
        homeViewModel.newsList.observe(this, Observer {
            newsAdaptor.submitList(it)
            homeViewModel.isLoading.postValue(false)
        })
    }

    /**
     * setup news list layout and setting up news adaptor
     */
    private fun setupNewsList(){
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = newsAdaptor
    }

    /**=============================================================================================*
     * NAVIGATION DRAWER - START
     *==============================================================================================*/

    override fun setupDrawer() {
        initDrawerGesture()

        drawerHandler.setup(
            findNavController(),
            DrawerConfigSettings.REMOVE_ALL
        )
    }

    /**
     * initialize drawer gesture to show and hide drawer gesture
     */
    private fun initDrawerGesture(){
        homeViewModel.isDrawerOpen.observe(viewLifecycleOwner, Observer {
            if(it){
                drawerHandler.showDrawer()
            }else{
                drawerHandler.hideDrawer()
            }
        })
    }

    /**
     * setting up bottom navigation bar to move between other tabs
     */
    private fun setupBottomNavigationBar(){
        NavigationUI.setupWithNavController(homeFragmentBinding.bttmNav,findNavController())
    }

    /**=============================================================================================*
     * NAVIGATION DRAWER - END
     *==============================================================================================*/

    /**=============================================================================================*
     * HARDWARE BACK BUTTON LISTENER - START
     *==============================================================================================*/

    /**
     * initialize back listener for identifying tap events of hardware back button
     */
    private fun initBackListener() {
        findNavController().currentDestination?.let {
            backHandler.bindBackPressedListener(
                view,
                it.id,
                R.id.homeFragment,
                onBackPressed
            )
        }
    }

    /**
     * this method use as callback listener of hardware back button
     */
    private val onBackPressed : (currentDestinationId : Int) -> Unit = {
        Timber.i("YD -> Home on back pressed -> ")
    }

    /**=============================================================================================*
     * HARDWARE BACK BUTTON LISTENER - END
     *==============================================================================================*/


    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/



}