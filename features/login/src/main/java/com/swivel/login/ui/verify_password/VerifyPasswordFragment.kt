package com.swivel.login.ui.verify_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.swivel.core.ui.BaseFragment
import com.swivel.login.databinding.VerifyPasswordFragmentBinding
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import kotlinx.android.synthetic.main.verify_password_fragment.*
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class VerifyPasswordFragment
 *
 * verify password which is entered by user, if user entered invalid password shows error message
 * otherwise execute authorization process and redirect to home page
 */
class VerifyPasswordFragment : BaseFragment() {

    @Inject
    lateinit var verifyPasswordViewModel : VerifyPasswordViewModel

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return VerifyPasswordFragmentBinding.inflate(inflater,container,false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = verifyPasswordViewModel
                forData = verifyPasswordViewModel.verifyPasswordFormData
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        initializePasswordTextField()
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
        Timber.i("YC -> PAGE NAVIGATION ID - INIT LOADER BEFORE SET -> $pageNavigationId")
        pageNavigationId = findNavController().currentDestination?.id
        Timber.i("YC -> PAGE NAVIGATION ID - INIT LOADER AFTER SET -> $pageNavigationId")
        pageNavigationId?.let { pageNavId ->
            verifyPasswordViewModel.isLoading.observe(viewLifecycleOwner, Observer {
                Timber.i("YC -> PAGE NAVIGATION ID - INIT LOADER ON TRIGGERED -> $pageNavId")
                progressBarHandler.toggleProgressUI(router,findNavController(),it,pageNavId)
            })
        }
    }

    override fun initViews() {
        verifyPasswordViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.VERIFY_PASSWORD))
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
    private fun initializePasswordTextField(){
        passwordEditText?.requestFocus()
    }
}