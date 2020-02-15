package com.swivel.home.ui.home.headline_news

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
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.models.libs.navigation.enums.DrawerConfigSettings
import com.swivel.ui.base.helpers.back_handler.BackHandler
import kotlinx.android.synthetic.main.filtered_news_fragment.*
import kotlinx.android.synthetic.main.home_content_layout.*
import kotlinx.android.synthetic.main.home_content_layout.list
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Yohan Dushmantha
 * @class HomeFragment
 */
class HomeFragment : BaseFragment(){

    @Inject lateinit var homeViewModel: HomeViewModel
    @Inject lateinit var backHandler: BackHandler

    private lateinit var homeFragmentBinding : HomeFragmentBinding
    private val newsAdaptor : NewsAdaptor = NewsAdaptor()

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
    }

    override fun initViews() {
        setupDrawer()
        setupBottomNavigationBar()
        initializeNewsListUpdateListner()
        initNewsList()
        homeViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.HOME_MAIN))
    }

    private fun initializeNewsListUpdateListner(){
        homeViewModel.newsList.observe(this, Observer {
            newsAdaptor.submitList(it)
        })
    }

    private fun initNewsList(){
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