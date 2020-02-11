package com.swivel.shared.ui.confirm_box

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.swivel.models.features.shared.confirm_box.enums.ConfirmBoxAppearance
import com.swivel.models.features.shared.confirm_box.enums.ConfirmBoxButtonAppearance
import com.swivel.models.features.shared.confirm_box.router_arguments.ConfirmBoxButtonConfig
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import com.swivel.shared.databinding.ConfirmBoxBottomSheetFragmentBinding
import com.swivel.ui.base.BaseBottomSheetDialogFragment
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class ConfirmBox
 */

class ConfirmBox : BaseBottomSheetDialogFragment() {

    @Inject
    lateinit var confirmBoxViewModel: ConfirmBoxViewModel

    @Inject
    lateinit var router: Router

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ConfirmBoxBottomSheetFragmentBinding.inflate(inflater,container,false).apply {
            viewModel = confirmBoxViewModel
            lifecycleOwner = this@ConfirmBox
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initViews()
    }

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initObservers() {
        initViewConfigObserver()
    }

    override fun initViews() {
        confirmBoxViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.SHARED_CONFIRM_BOX))
    }

    override fun initLoader() {
        initLoader()
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * CONFIG VIEW - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * initialize view observers and config view according to viewConfig object
     */
    private fun initViewConfigObserver(){
        confirmBoxViewModel.viewConfig.observe(this, Observer {
            configCancellableStatus(it?.isCancellable)
            configConfirmBoxAppearance(it?.confirmBoxAppearance)
            configButtons(it?.buttonList)
            configDismissEvent()
        })
    }

    /**
     * config cancellable status of bottom sheet
     * @param isCancellable if isCancellable status true, allow users to cancel or hide bottom sheet
     * without clicking positive or negative button, otherwise prevent hide the bottom sheet until
     * clicking the buttons
     * default isCancellable value is false
     */
    private fun configCancellableStatus(isCancellable : Boolean?){
        isCancellable?.let {
            isCancelable = it
        }
    }

    /**
     * config confirm box appearance according to confirmBoxAppearance status.
     * currently there are is only one appearance, if you need more appearance you can config
     * confirm box according to your application theme
     * @param appearance the status which is considered to change the confirm box appearance
     */
    private fun configConfirmBoxAppearance(appearance: ConfirmBoxAppearance?){
        when(appearance){
            ConfirmBoxAppearance.DEFAULT ->{

            }
        }
    }

    /**
     * config buttons appearance according to received buttonConfigs
     * if need to do more customization according to ConfirmBoxButtonAppearance, this is the place
     * to do it or otherwise can be changed it using styles_confirm_box.xml file
     * @param buttonConfigList list of ConfirmBoxButtonConfig, these objects consists with
     * button related customizable data
     */
    private fun configButtons(buttonConfigList : HashMap<Int, ConfirmBoxButtonConfig>?){
        buttonConfigList?.forEach { (i, confirmBoxButtonConfig) ->
            when(i){
                ConfirmBoxButtonAppearance.POSITIVE.appearanceID -> {

                }
                ConfirmBoxButtonAppearance.NEGATIVE.appearanceID -> {

                }
            }
        }
    }

    /**
     * config dismiss event of Confirm Box
     * confirm box has been observed to dismissUI and dismissible behaviour of the confirm box will
     * change according to dismissUI property of viewModel
     */
    private fun configDismissEvent(){
        confirmBoxViewModel.dismissUI.observe(this, Observer {
            it?.let {
                if(it){ dismiss() }
            }
        })
    }


    /**---------------------------------------------------------------------------------------------*
     * CONFIG VIEW - END
     *----------------------------------------------------------------------------------------------*/
}