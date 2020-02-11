package com.swivel.core.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.ui.base.helpers.info_box_handler.InfoBoxHandler
import javax.inject.Inject


/**
 * @author Yohan Dushmantha
 * @class BaseViewModel
 *
 * BaseViewModel should be implemented by every view model
 *
 * All kind of common methods which is related to view models should be added here
 *
 * provides abstract methods for view models to protect consistency, readability , and maintainability
 * of the code
 *
 */

abstract class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    @Inject lateinit var infoBoxHandler: InfoBoxHandler

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * initialize View from received arguments and bind values to liveData Objects
     * @param deepLinkArguments received deep link arguments object from navigator
     */
    abstract fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?)

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/


}