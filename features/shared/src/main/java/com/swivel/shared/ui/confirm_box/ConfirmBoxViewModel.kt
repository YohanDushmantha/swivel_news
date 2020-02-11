package com.swivel.shared.ui.confirm_box

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.shared.confirm_box.enums.ConfirmBoxAppearance
import com.swivel.models.features.shared.confirm_box.enums.ConfirmBoxButtonAppearance
import com.swivel.models.features.shared.confirm_box.router_arguments.ConfirmBoxButtonConfig
import com.swivel.models.features.shared.confirm_box.router_arguments.ConfirmBoxDeepLinkArguments
import com.swivel.models.features.shared.confirm_box.router_arguments.ConfirmBoxViewConfig
import com.swivel.shared.R
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class ConfirmBoxViewModel
 */

class ConfirmBoxViewModel @Inject constructor(
    private val context: Context
) : BaseViewModel() {

    var confirmBoxID : Short = 0
    val title = MutableLiveData<String>()
    val detail = MutableLiveData<String>()
    val viewConfig = MutableLiveData<ConfirmBoxViewConfig?>()
    val positiveButtonConfig = MutableLiveData<ConfirmBoxButtonConfig>()
    val negativeButtonConfig = MutableLiveData<ConfirmBoxButtonConfig>()
    val dismissUI = MutableLiveData<Boolean>()

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? ConfirmBoxDeepLinkArguments)?.let { deepLinkArgument ->
            confirmBoxID = deepLinkArgument.confirmBoxId
            setTitle(deepLinkArgument.title,deepLinkArgument.viewConfig?.confirmBoxAppearance)
            detail.postValue(deepLinkArgument.detail)

            deepLinkArgument.viewConfig?.let {
                viewConfig.postValue(it)
                initButtonConfigsValues(it)
            }
        }
    }

    /**
     * initialize button related config values according to ConfirmBoxViewConfig
     * @param confirmBoxViewConfig ViewConfig object of Confirm Box
     */
    private fun initButtonConfigsValues(confirmBoxViewConfig: ConfirmBoxViewConfig){
        confirmBoxViewConfig.buttonList?.get(ConfirmBoxButtonAppearance.POSITIVE.appearanceID)?.let {
            positiveButtonConfig.postValue(it)
        }
        confirmBoxViewConfig.buttonList?.get(ConfirmBoxButtonAppearance.NEGATIVE.appearanceID)?.let {
            negativeButtonConfig.postValue(it)
        }
    }

    /**
     * set provided title or setting up default title for related confirm box type
     * @param providedTitle provided title
     * @param confirmBoxAppearance confirm box appearance configuration
     */
    private fun setTitle(providedTitle : String?, confirmBoxAppearance : ConfirmBoxAppearance?){
        providedTitle?.let {
            title.postValue(providedTitle)
        } ?: run {
            when(confirmBoxAppearance){
                ConfirmBoxAppearance.DEFAULT ->{title.postValue(context.getString(R.string.confirm_box_title_default))}
            }
        }

    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * trigger when clicking positive button of confirm box
     * @param view view object that is clicked
     */
    fun onTapPositiveButton(view : View?){
        viewConfig.value?.buttonList?.get(ConfirmBoxButtonAppearance.POSITIVE.appearanceID)?.let {
            it.callbackFunction(confirmBoxID,
                ConfirmBoxButtonAppearance.POSITIVE,null)
        }
        dismissUI.postValue(true)
    }

    /**
     * trigger when clicking negative button of confirm box
     * @param view view object that is clicked
     */
    fun onTapNegativeButton(view : View?){
        viewConfig.value?.buttonList?.get(ConfirmBoxButtonAppearance.NEGATIVE.appearanceID)?.let {
            it.callbackFunction(confirmBoxID,
                ConfirmBoxButtonAppearance.NEGATIVE,null)
        }
        dismissUI.postValue(true)
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/
}