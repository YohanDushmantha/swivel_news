package com.swivel.onboarding.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.swivel.core.ui.BaseFragment
import com.swivel.models.features.home.home.router_arguments.HomeDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.onboarding.databinding.SplashFragmentBinding
import com.swivel.ui.base.helpers.custom_dots_progress_bar_animator.CustomDotsProgressBarAnimator
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class SplashFragment
 */
class SplashFragment : BaseFragment() {

    @Inject lateinit var splashViewModel : SplashViewModel
    @Inject lateinit var customDotsProgressBarAnimator: CustomDotsProgressBarAnimator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //initialize Data Binding
        return SplashFragmentBinding.inflate(inflater,container,false)
            .apply {
                // initialize data binding varibales here
                lifecycleOwner = viewLifecycleOwner
                viewModel = splashViewModel
            }
            .root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("YC -> TASK onViewCreated")
        initObservers()
        initViews()
    }

    override fun onStart() {
        super.onStart()
        splashViewModel.initializeApp(findNavController())
    }


    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initObservers() {
        initLoader()
        initPhoneStatePermissionListener()
        initAccessNetworkStatePermissionListener()
        initRedirectionListeners()
        initAppClosingListner()
    }

    override fun initLoader() {
        pageNavigationId = findNavController().currentDestination?.id
        pageNavigationId?.let { pageNavId ->
            splashViewModel.isLoading.observe(viewLifecycleOwner, Observer {
                progressBarHandler.toggleProgressUI(router,findNavController(),it,pageNavId)
            })
        }
    }

    override fun initViews() {
        setupDrawer()
    }

    override fun setupDrawer() {

    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * PERMISSION HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        splashViewModel.onRequestPermissionsResult(requestCode,permissions,grantResults)
    }

    /**
     * bind permission request listener for phone state permission
     */
    private fun initPhoneStatePermissionListener() {
        splashViewModel.isRequestToGrantPhoneStatePermission.observe(viewLifecycleOwner, Observer {
            if(it){
                requestPermissions(arrayOf(android.Manifest.permission.READ_PHONE_STATE),splashViewModel.phoneStatePermissionRequestCode)
            }
        })
    }

    /**
     * bind permission request listener for access network state permission
     */
    private fun initAccessNetworkStatePermissionListener(){
        splashViewModel.isRequestToGrantAccessNetworkPermission.observe(viewLifecycleOwner, Observer {
            if(it){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_NETWORK_STATE), splashViewModel.accessNetworkStatePermissionRequestCode)
            }
        })
    }

    /**
     * bind redirection listeners for home ui and walkthrough ui
     */
    private fun initRedirectionListeners(){
        splashViewModel.isRedirectToHomeUI.observe(viewLifecycleOwner, Observer {
            if(it){
                router.route(findNavController(), DEEP_LINK.HOME_MAIN,null, HomeDeepLinkArguments().apply {

                })
            }
        })

        splashViewModel.isRedirectTpWalkthroughUI.observe(viewLifecycleOwner, Observer {
            if(it){
                router.route(findNavController(),SplashFragmentDirections.actionSplashFragmentToWalkthroughFragment().apply {

                })
            }
        })


    }

    /**---------------------------------------------------------------------------------------------*
     * PERMISSION HANDLING - END
     *----------------------------------------------------------------------------------------------*/

    /**
     * bind app closing listener
     */
    private fun initAppClosingListner(){
        splashViewModel.shouldCloseApp.observe(viewLifecycleOwner, Observer {
            if(it){
                closeApp(activity)
            }
        })
    }

}
