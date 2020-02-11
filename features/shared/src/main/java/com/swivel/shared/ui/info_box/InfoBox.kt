package com.swivel.shared.ui.info_box

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.swivel.models.features.shared.info_box.enums.InfoBoxAppearance
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import com.swivel.shared.R
import com.swivel.shared.databinding.InfoBoxDialogFragmentBinding
import com.swivel.ui.base.BaseDialogFragment
import kotlinx.android.synthetic.main.info_box_dialog_fragment.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class InfoBox
 */

class InfoBox : BaseDialogFragment() {

    @Inject
    lateinit var infoBoxViewModel : InfoBoxViewModel

    @Inject
    lateinit var router : Router

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return InfoBoxDialogFragmentBinding.inflate(inflater,container,false).apply {
            viewModel = infoBoxViewModel
            lifecycleOwner = this@InfoBox
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initViews()

    }

    override fun onStart() {
        super.onStart()
        removefixHorizontalMarginsOfDialogFragment()
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
        infoBoxViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.SHARED_INFO_BOX))
    }

    override fun initLoader() {
        initLoader()
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/


    /**---------------------------------------------------------------------------------------------*
     * CONFIG VIEW - END
     *----------------------------------------------------------------------------------------------*/

    private fun initViewConfigObserver(){
        infoBoxViewModel.viewConfig.observe(this, Observer {

            configInfoBoxPosition(it?.gravity!!)
            hideInfoBoxWhenTouchingOutside(it.isHideWhenTouchingOutsideOfInfoBox)
            configAutoHideInfoBox(it.isAutoHide,it.visibleTime)
            configInfoBoxByType(it.infoBoxType)
            hideInfoBoxWhenTouching(it.isHideWhenTouchingInfoBox,it.onTapInfoBoxCallback)
            configDismissEvent()
        })
    }

    /**
     * config info box position inside view
     * this can be top, bottom or center vertical
     * @param gravity position of InfoBox
     */
    private fun configInfoBoxPosition(gravity : Int){
        dialog?.window?.setGravity(gravity)
    }

    /**
     * Config to Auto Hide Info Box
     *
     * if isAutoHide is true, InfoBox should be dismiss after passed time by visibleTime
     *
     * @param isAutoHide status of visiblity of Info Box
     * @param visibleTime info box maximum visible time
     */
    private fun configAutoHideInfoBox(isAutoHide : Boolean, visibleTime : Long){
        if(isAutoHide){
            Executors.newSingleThreadScheduledExecutor().schedule({
                dismiss()
            },visibleTime, TimeUnit.MILLISECONDS)
        }
    }

    /**
     * config info box appearance according to InfoBoxAppearance
     * @param messageAppearance appearance type of the InfoBoxAppearance
     */
    private fun configInfoBoxByType(messageAppearance : InfoBoxAppearance){
        when(messageAppearance){

            InfoBoxAppearance.INFO -> {
                configInfoBox(R.drawable.info_gradient_bg,R.drawable.ic_info)
            }
            InfoBoxAppearance.WARNING -> {
                configInfoBox(R.drawable.warning_gradient_bg,R.drawable.ic_warning)
            }
            InfoBoxAppearance.ERROR -> {
                configInfoBox(R.drawable.error_gradient_bg,R.drawable.ic_error)
            }
            InfoBoxAppearance.SUCCESS -> {
                configInfoBox(R.drawable.success_gradient_bg,R.drawable.ic_success)
            }
        }

        info_title?.setTextColor(ContextCompat.getColor(context!!,R.color.info_box_default_title_color))
        info_detail?.setTextColor(ContextCompat.getColor(context!!,R.color.info_box_default_detail_color))
    }

    /**
     * config infoBox
     * @param gradient drawable id of gradient
     * @param icon drawable id of icon
     */
    private fun configInfoBox(gradient : Int, icon : Int){
        infoBoxContainer?.setBackgroundResource(gradient)
        info_logo?.setImageResource(icon)
    }

    /**
     * hide info box when touching it at very first time that info box is clicked
     *
     * @param isHide hide or visible status
     * @param onTapInfoBox listener for infoBox events
     */
    private fun hideInfoBoxWhenTouching(
        isHide : Boolean
        ,onTapInfoBox : ((infoBoxId : Short) -> Unit)? = null
       )
    {
        if(isHide){
            view?.setOnClickListener {
                infoBoxViewModel.onTapInfoBox(onTapInfoBox)

                //dismiss the application
                dismiss()
            }
        }
    }

    /**
     * hide info box when touching it at very first time that outside of the info box is clicked
     *
     * @param isHide hide or visible status
     */
    private fun hideInfoBoxWhenTouchingOutside(
        isHide : Boolean
    ) {
        dialog?.setCanceledOnTouchOutside(isHide)
    }

    /**
     * config dismiss event of Info Box
     * info box has been observed to dismissUI and dismissible behaviour of the info box will
     * change according to dismissUI property of viewModel
     */
    fun configDismissEvent(){
        infoBoxViewModel.dismissUI.observe(this, Observer {
            it?.let {
                if (it){
                    dismiss()
                }
            }
        })
    }

    /**---------------------------------------------------------------------------------------------*
     * CONFIG VIEW - END
     *----------------------------------------------------------------------------------------------*/
}