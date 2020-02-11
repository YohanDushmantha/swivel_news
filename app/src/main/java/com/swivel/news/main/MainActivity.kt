package com.swivel.news.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.swivel.models.features.shared.info_box.enums.InfoBoxAppearance
import com.swivel.models.features.shared.info_box.router_arguments.InfoBoxDeepLinkArguments
import com.swivel.models.features.shared.info_box.router_arguments.InfoBoxViewConfig
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.models.libs.navigation.enums.DrawerConfigSettings
import com.swivel.navigation.router.Router
import com.swivel.ui.base.extentions.loadCircularImage
import com.swivel.ui.base.helpers.drawer_handler.IDrawerHandler
import com.swivel.utility.network_connection_manager.INetworkConnectionManager
import com.swivel.news.R
import com.swivel.news.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_layout.view.*
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Yohan Dushmantha
 * @class MainActivity
 *
 * initial activity of the application and behave as a Main Nav Host of the application
 */
class MainActivity  : DaggerAppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    @Inject lateinit var mainViewModel: MainViewModel
    @Inject lateinit var router: Router
    @Inject lateinit var drawerHandler: IDrawerHandler
    @Inject lateinit var networkConnectionManager : INetworkConnectionManager

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        ).apply {
            viewModel = mainViewModel
        }


        mainViewModel.loadAppConfigs()
        initObservers()

        mainViewModel.initViewArguments(null)

        setupNavigation(findNavController(R.id.holder))
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun initObservers(){
        initInternetAvailabilityChecker()
    }

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * NAVIGATION DRAWER SETUP - START
     *----------------------------------------------------------------------------------------------*/

    private val  drawerProfileDataUpdateCallback : () -> Unit = {
        Timber.i("YD -> DRAWER PROFILE DATA UPDATION CALLBACK.")
        navigationView?.getHeaderView(0)?.profileImage?.loadCircularImage(
            "https://s3-ap-southeast-1.amazonaws.com/youcabs-dev/driver/profile/6085a0d1-3a53-4713-a078-066d9660dfaf",
            4f
        )
    }

    /**
     * setting up one time navigation
     * @param navController navigation controller
     */
    private fun setupNavigation(navController : NavController) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)


        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)

        //initialze and setup drawer
        drawerHandler.initialize(supportActionBar, drawerLayout,drawerProfileDataUpdateCallback)
        drawerHandler.setup(navController, DrawerConfigSettings.REMOVE_ALL)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            findNavController(R.id.holder),
            drawerLayout
        )
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true
        drawerLayout.closeDrawers()
        when (menuItem.itemId) {
            R.id.accountMenuItem -> findNavController(R.id.holder).navigate(R.id.homeFragment)
        }
        return true
    }

    /**---------------------------------------------------------------------------------------------*
     * NAVIGATION DRAWER SETUP - END
     *----------------------------------------------------------------------------------------------*/


    /**---------------------------------------------------------------------------------------------*
     * INTERNET ACCESSIBILITY CHECKING - START
     *----------------------------------------------------------------------------------------------*/

    private fun initInternetAvailabilityChecker(){
        mainViewModel.isInternetAlive.observe(this, Observer
            {
                toggleNetworkAccessibilityIssueMessage(
                    router,
                    !it,
                    applicationContext
                )
            }
        )
    }

    private val onTapInfoBox : (errorCode : Short) -> Unit = {
        when(it){
            MainInfoBoxID.INTERNET_ACCESSIBILITY_ISSUE.infoBoxID -> {
                networkConnectionManager.requestToCheckInternetAccessibility()
            }
        }
    }

    fun toggleNetworkAccessibilityIssueMessage(
        router: Router?,
        visibility: Boolean,
        context: Context
    ){
        if(visibility){
            router?.route(
                findNavController(R.id.holder),
                DEEP_LINK.SHARED_INFO_BOX,
                null,
                InfoBoxDeepLinkArguments().apply {
                    infoBoxId = MainInfoBoxID.INTERNET_ACCESSIBILITY_ISSUE.infoBoxID
                    detail = context.getString(R.string.internet_accessibility_issue)
                    viewConfig = InfoBoxViewConfig().apply {
                        infoBoxType = InfoBoxAppearance.WARNING
                        isAutoHide = false
                        isHideWhenTouchingOutsideOfInfoBox = false
                        onTapInfoBoxCallback = onTapInfoBox
                    }
                }
            )
        }else{
            router?.dismissDialogFragment(
                findNavController(R.id.holder),
                R.id.infoBox
            )
        }
    }



    /**---------------------------------------------------------------------------------------------*
     * INTERNET ACCESSIBILITY CHECKING - END
     *----------------------------------------------------------------------------------------------*/


}
