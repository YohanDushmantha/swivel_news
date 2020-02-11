package com.swivel.home.ui.home

import android.content.Context
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.swivel.core.ui.BaseViewModel
import com.swivel.home.R
import com.swivel.home.ui.home.exceptions.HomeViewModelException
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.home.home.enums.StatisticsDisplayType
import com.swivel.models.features.home.home.router_arguments.HomeDeepLinkArguments
import com.swivel.navigation.router.Router
import com.swivel.ui.base.helpers.animation_handler.bounce_handler.BounceAnimationHandler
import com.swivel.ui.base.helpers.animation_handler.rotation_handler.RotateAnimationHandler
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class PostViewModel
 */
class HomeViewModel @Inject constructor(
    private val router: Router,
    private val context: Context,
    private val bounceAnimationHandler: BounceAnimationHandler,
    private val rotateAnimationHandler: RotateAnimationHandler
) : BaseViewModel(){

    val isDrawerOpen : MutableLiveData<Boolean> = MutableLiveData()
    val statisticsSpannableString : MutableLiveData<SpannableString> = MutableLiveData()
    val receivedViewArguments : MutableLiveData<HomeDeepLinkArguments> = MutableLiveData()

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        deepLinkArguments?.let {
            receivedViewArguments.postValue(it as? HomeDeepLinkArguments)
        }

        setValuesToStatisticsBoard(
            "Rs 64.50",
            StatisticsDisplayType.TODAY_EARNINGS
        )
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * trigger when tapping on profile image
     * @param view target view
     */
    fun onTapProfile(view : View?){
        try {
            view?.let{
                bounceAnimationHandler.animate(it)
                isDrawerOpen.postValue(true)
            }
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    /**
     * trigger when tapping on destination search icon
     * @param view target view
     */
    fun onTapDestinationSearch(view: View?){
        try {
            view?.let{
                bounceAnimationHandler.animate(it)
            }
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    /**
     * trigger when tapping on shield icon
     * @param view target view
     */
    fun onTapShield(view: View?){
        try {
            view?.let{
                bounceAnimationHandler.animate(it)
            }
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    /**
     * trigger when tapping on gps icon
     * @param view target view
     */
    fun onTapGps(view : View?){
        try {
            view?.let{
                bounceAnimationHandler.animate(it)
            }
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    /**
     * trigger when tapping on go icon
     * @param view target view
     */
    fun onTapGo(view: View?){
        try {
            view?.let{
                bounceAnimationHandler.animate(it)
            }
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    /**
     * trigger when tapping on statistics display
     * @param view target view
     */
    fun onTapStatisticsDisplay(view : View?){
        try {
            view?.let{
                bounceAnimationHandler.animate(it)

            }
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * STATISTICS BOARD - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * set values to statistics board
     * @param text values to display
     * @param selectedStatisticsDisplayType selectable statistics display type. display format
     * should be changed according to selectedStatisticsDisplayType
     */
    private fun setValuesToStatisticsBoard(
        text : String?,
        selectedStatisticsDisplayType: StatisticsDisplayType = StatisticsDisplayType.TODAY_EARNINGS
    ){
        try {
            text?.let {
                val spannableString = SpannableString(it)
                when(selectedStatisticsDisplayType){
                    StatisticsDisplayType.LAST_TRIP, StatisticsDisplayType.TODAY_EARNINGS -> {
                        
                        spannableString.setSpan(
                            ForegroundColorSpan(
                                ContextCompat.getColor(context,R.color.home_statistics_display_currency_symbol_color)
                            ),0,2,0
                        )
                        
                    }
                    
                    StatisticsDisplayType.QUEST -> {
                        
                        it.split("|").let { splitText ->
                            spannableString.setSpan(
                                ForegroundColorSpan(
                                    ContextCompat.getColor(context,R.color.home_statistics_display_currency_symbol_color)
                                ),0,splitText[0].length,0
                            )
                        }
                    }
                }
                statisticsSpannableString.postValue(spannableString)

            } ?: throw HomeViewModelException("Unable to set values to statistics board with empty value")
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }


    /**---------------------------------------------------------------------------------------------*
     * STATISTICS BOARD - END
     *----------------------------------------------------------------------------------------------*/


    /**---------------------------------------------------------------------------------------------*
     * DATA HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * DATA HANDLING - END
     *----------------------------------------------------------------------------------------------*/

}