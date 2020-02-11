package com.swivel.shared.ui.info_box

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.shared.info_box.enums.InfoBoxAppearance
import com.swivel.models.features.shared.info_box.router_arguments.InfoBoxDeepLinkArguments
import com.swivel.models.features.shared.info_box.router_arguments.InfoBoxViewConfig
import com.swivel.shared.R
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class InfoBoxViewModel
 */
class InfoBoxViewModel @Inject constructor(
    private val context: Context
) : BaseViewModel() {

    var infoBoxID : Short = 0
    val title = MutableLiveData<String>()
    val detail = MutableLiveData<String>()
    val viewConfig = MutableLiveData<InfoBoxViewConfig?>()
    val dismissUI = MutableLiveData<Boolean>()

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? InfoBoxDeepLinkArguments)?.let { deepLinkArgument ->
            infoBoxID = deepLinkArgument.infoBoxId
            title.postValue(deepLinkArgument.title)
            setTitle(deepLinkArgument.title,deepLinkArgument.viewConfig?.infoBoxType)
            detail.postValue(deepLinkArgument.detail)

            deepLinkArgument.viewConfig?.let {
                viewConfig.postValue(it)
            }

        }
    }

    /**
     * set provided title or setting up default title for related info box type
     * @param providedTitle provided title
     * @param infoBoxAppearance info box appearance configuration
     */
    private fun setTitle(providedTitle : String?, infoBoxAppearance: InfoBoxAppearance?){
        providedTitle?.let {
            title.postValue(providedTitle)
        } ?: run {
            when(infoBoxAppearance){
                InfoBoxAppearance.INFO ->{title.postValue(context.getString(R.string.info_box_default_title_info))}
                InfoBoxAppearance.WARNING -> {title.postValue(context.getString(R.string.info_box_default_title_warning))}
                InfoBoxAppearance.SUCCESS -> {title.postValue(context.getString(R.string.info_box_default_title_success))}
                InfoBoxAppearance.ERROR -> {title.postValue(context.getString(R.string.info_box_default_title_error))}
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
     * trigger when clicking on the surface of info box
     * @param onTapInfoBox the callback function for handling the event
     */
    fun onTapInfoBox( onTapInfoBox : ((infoBoxId : Short) -> Unit)? = null){
        //trigger onTapEvent of InfoBox
        onTapInfoBox?.let {
                it(infoBoxID)
        }

        dismissUI.postValue(true)
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/
}