package com.swivel.shared.ui.verify_mobile_number

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.swivel.core.ui.BaseFragment
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.shared.databinding.VerifyMobileNumberFragmentBinding
import kotlinx.android.synthetic.main.verify_mobile_number_fragment.*
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class VerifyMobileNumberFragment
 */
class VerifyMobileNumberFragment : BaseFragment() {

    @Inject
    lateinit var verifyMobileNumberViewModel : VerifyMobileNumberViewModel

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return VerifyMobileNumberFragmentBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = this@VerifyMobileNumberFragment
            viewModel = verifyMobileNumberViewModel
            formData = viewModel?.verifyMobileNumberFormData
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        initializeMobileNumberTextField()
    }

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initObservers() {
        initLoader()
    }

    override fun initLoader() {
        pageNavigationId = findNavController().currentDestination?.id
        pageNavigationId?.let { pageNavId ->
            verifyMobileNumberViewModel.isLoading.observe(viewLifecycleOwner, Observer {
                progressBarHandler.toggleProgressUI(router,findNavController(),it,pageNavId)
            })
        }
    }

    override fun initViews() {
        verifyMobileNumberViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.SHARED_VERIFY_MOBILE_NUMBER))
        setupDrawer()
    }

    override fun setupDrawer() {

    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**
     * setting up mobile number text field when starting fragment
     */
    private fun initializeMobileNumberTextField(){
        mobileNumberEditText?.requestFocus()
    }
}