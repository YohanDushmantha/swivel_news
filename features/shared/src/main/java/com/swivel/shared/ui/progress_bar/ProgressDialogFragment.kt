package com.swivel.shared.ui.progress_bar

import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import com.swivel.shared.databinding.ProgressDialogFragmentBinding
import com.swivel.ui.base.BaseDialogFragment
import kotlinx.android.synthetic.main.progress_dialog_fragment.*
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class ProgressDialogFragment
 */
class ProgressDialogFragment : BaseDialogFragment() {

    @Inject
    lateinit var progressDialogViewModel : ProgressDialogViewModel

    @Inject
    lateinit var router: Router

    lateinit var vehicleAnimation : ValueAnimator

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        isCancelable = false
        dialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        return ProgressDialogFragmentBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = this@ProgressDialogFragment
            viewModel = progressDialogViewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initLoader()
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        animateVehicle(false)
    }

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initObservers() {

    }

    override fun initLoader() {

    }

    override fun initViews() {
        progressDialogViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.SHARED_PROGRESS_BAR))
        initVehicleAnimation()
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * VEHICLE ANIMATION - START
     *----------------------------------------------------------------------------------------------*/

    fun initVehicleAnimation(){

        val layoutParams = mCarIcon.layoutParams as ConstraintLayout.LayoutParams
        val startAngle = layoutParams.circleAngle
        val endAngle = startAngle + 360

        vehicleAnimation = ValueAnimator.ofFloat(startAngle, endAngle)
        vehicleAnimation.addUpdateListener { valueAnimator ->


            val animatedValue = valueAnimator.animatedValue as Float
            val layoutParams = mCarIcon?.layoutParams as ConstraintLayout.LayoutParams
            layoutParams.circleAngle = animatedValue
            mCarIcon?.layoutParams = layoutParams

            mCarIcon?.rotation = (animatedValue % 360 - 270)
        }

        vehicleAnimation.duration = 4000
        vehicleAnimation.repeatCount = -1
        vehicleAnimation.repeatMode = ValueAnimator.RESTART

        vehicleAnimation.interpolator = LinearInterpolator()

        animateVehicle(true)
    }

    fun animateVehicle(animate : Boolean){
        vehicleAnimation.cancel()
        if(animate){
            vehicleAnimation.start()
        }

    }

    /**---------------------------------------------------------------------------------------------*
     * VEHICLE ANIMATION - END
     *----------------------------------------------------------------------------------------------*/

}