package com.swivel.home.ui.trip_planner

import android.content.Context
import android.view.View
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.navigation.router.Router
import com.swivel.ui.base.helpers.animation_handler.bounce_handler.BounceAnimationHandler
import com.swivel.ui.base.helpers.animation_handler.rotation_handler.RotateAnimationHandler
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class TripPlannerViewModel
 *
 * ViewModel for Trip Planner persistent bottom sheet
 */
class TripPlannerViewModal @Inject constructor(
    private val router: Router,
    private val context: Context,
    private val bounceAnimationHandler: BounceAnimationHandler,
    private val rotateAnimationHandler: RotateAnimationHandler
) : BaseViewModel(){

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {

    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * trigger when tapping on expandable carrot icon
     * @param view target view
     */
    fun onTapExpandableCarrot(view: View?){
        try {
            view?.let{
                rotateAnimationHandler.animate(it,180f)
            }
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    /**
     * trigger when tapping on list icon
     * @param view target view
     */
    fun onTapList(view: View?){
        try {
            view?.let{
                rotateAnimationHandler.animate(it)
            }
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/

}