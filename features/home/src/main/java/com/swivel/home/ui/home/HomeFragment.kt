package com.swivel.home.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.swivel.core.ui.BaseFragment
import com.swivel.home.R
import com.swivel.home.databinding.HomeFragmentBinding
import com.swivel.home.databinding.TripPlannerPersistantBottomSheetLayoutBinding
import com.swivel.home.ui.trip_planner.TripPlannerViewModal
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.models.libs.navigation.enums.DrawerConfigSettings
import com.swivel.ui.base.extentions.loadCircularImage
import com.swivel.ui.base.helpers.back_handler.BackHandler
import kotlinx.android.synthetic.main.home_content_layout.*
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Yohan Dushmantha
 * @class HomeFragment
 */
class HomeFragment : BaseFragment(){

    @Inject lateinit var homeViewModel: HomeViewModel
    @Inject lateinit var tripPlannerVM: TripPlannerViewModal
    @Inject lateinit var backHandler: BackHandler

    private lateinit var homeFragmentBinding : HomeFragmentBinding

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
            tripPlannerViewModel = tripPlannerVM
        }
        return  homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
        initBackListener()
        initTripPlannerBottomSheet()
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
        homeViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.HOME_MAIN))
    }

    /**=============================================================================================*
     * NAVIGATION DRAWER - START
     *==============================================================================================*/

    override fun setupDrawer() {
        initDrawerGesture()

        drawerHandler.setup(
            findNavController(),
            DrawerConfigSettings.ONLY_ENABLE_DRAWER_GESTURE
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

    /**=============================================================================================*
     * INIT TRIP PLANNER BOTTOM SHEET - START
     *==============================================================================================*/

    fun initTripPlannerBottomSheet(){


        homeFragmentBinding.tripPlannerContainer.tripPlannerBottomSheet?.let {
            BottomSheetBehavior.from(it).setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            Timber.i("YD -> Trip Planner bottom sheet STATE HIDDEN")
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            Timber.i("YD -> Trip Planner bottom sheet STATE_EXPANDED")
                        }
                        // btBottomSheet.text = "Close Bottom Sheet"
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            Timber.i("YD -> Trip Planner bottom sheet STATE_COLLAPSED")
                        }
                        // btBottomSheet.text = "Expand Bottom Sheet"
                        BottomSheetBehavior.STATE_DRAGGING -> {
                            Timber.i("YD -> Trip Planner bottom sheet STATE_DRAGGING")
                        }
                        BottomSheetBehavior.STATE_SETTLING -> {
                            Timber.i("YD -> Trip Planner bottom sheet STATE_SETTLING")
                        }
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
        } ?: Timber.i("YD -> Trip Planner bottom sheet null")
    }

    /**=============================================================================================*
     * INIT TRIP PLANNER BOTTOM SHEET - END
     *==============================================================================================*/


    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

}