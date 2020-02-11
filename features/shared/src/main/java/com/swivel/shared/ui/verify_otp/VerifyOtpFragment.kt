package com.swivel.shared.ui.verify_otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.swivel.core.ui.BaseFragment
import com.swivel.models.libs.navigation.enums.DrawerConfigSettings
import com.swivel.shared.databinding.VerifyOtpFragmentBinding
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class VerifyOtpFragment
 */
class VerifyOtpFragment : BaseFragment() {

    @Inject
    lateinit var verifyOTPViewModel: VerifyOtpViewModel

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return VerifyOtpFragmentBinding.inflate(inflater,container,false)
            .apply {
                lifecycleOwner = this@VerifyOtpFragment
                viewModel = verifyOTPViewModel
            }
            .root
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

    }

    override fun initLoader() {

    }

    override fun initViews() {
        setupDrawer()
    }

    override fun setupDrawer() {
        drawerHandler.setup(
            findNavController(),
            DrawerConfigSettings.ONLY_SHOW_APP_BAR
        )
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/
}