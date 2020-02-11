package com.swivel.shared.ui.progress_bar

import androidx.lifecycle.MutableLiveData
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.shared.progress_bar.router_arguments.ProgressBarUIDeepLinkArguments
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class ProgressDialogViewModel
 */
class ProgressDialogViewModel @Inject constructor() : BaseViewModel() {

    var loadingText = MutableLiveData<String>()

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? ProgressBarUIDeepLinkArguments)?.let {
            loadingText.postValue(it.loadingText)
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/
}