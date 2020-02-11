package com.swivel.onboarding.ui.walkthrough

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.swivel.core.ui.BaseFragment
import com.swivel.models.libs.navigation.enums.DrawerConfigSettings
import com.swivel.onboarding.R
import com.swivel.onboarding.databinding.WalkthroughFragmentBinding
import com.swivel.ui.base.helpers.back_handler.BackHandler
import kotlinx.android.synthetic.main.walkthrough_fragment.*
import javax.inject.Inject


/**
 * @author Yohan Dushmantha
 * @class WalkthroughFragment
 */

class WalkthroughFragment : BaseFragment(){

    @Inject lateinit var walkthroughViewModel: WalkthroughViewModel
    @Inject lateinit var walkthroughViewPagerAdaptor : WalkthroughViewPagerAdaptor
    @Inject lateinit var backHandler: BackHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return WalkthroughFragmentBinding.inflate(inflater,container,false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = walkthroughViewModel
            }
            .root
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
        initAppClosingListner()
    }

    override fun initLoader() {

    }

    override fun initViews() {
        initializeViewPager(walkthroughViewPagerAdaptor)
        setupDrawer()
    }

    override fun setupDrawer() {
        drawerHandler.setup(
            findNavController(),DrawerConfigSettings.REMOVE_ALL
        )
    }

    /**=============================================================================================*
     * INIT VIEW PAGER - START
     *==============================================================================================*/

    /**
     * initialize view pager and bind view pager dots library
     */
    private fun initializeViewPager(walkthroughViewPagerAdaptor: WalkthroughViewPagerAdaptor){
        walkthroughViewPager?.adapter = walkthroughViewPagerAdaptor
        walkthroughViewPagerAdaptor.setItem(walkthroughViewModel.walkthroughPages)
        walkthroughDotsIndicator?.setViewPager2(walkthroughViewPager)
    }

    /**=============================================================================================*
     * INIT VIEW PAGER - END
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
                R.id.walkthroughFragment,
                onBackPressed
            )
        }
    }

    private val onBackPressed : (currentDestinationId : Int) -> Unit = {
        walkthroughViewModel.showExitConfirmation(findNavController())
    }

    /**=============================================================================================*
     * HARDWARE BACK BUTTON LISTENER - END
     *==============================================================================================*/

    /**=============================================================================================*
     * APP CLOSING - START
     *==============================================================================================*/

    /**
     * initialize listener for app closing event
     */
    private fun initAppClosingListner(){
        walkthroughViewModel.shouldCloseApp.observe(viewLifecycleOwner, Observer {
            if(it){
                closeApp(activity)
            }
        })
    }

    /**=============================================================================================*
     * APP CLOSING - END
     *==============================================================================================*/



    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

}