package com.swivel.login.ui.forgot_password_option

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.swivel.core.ui.BaseFragment
import com.swivel.login.databinding.ForgotPasswordOptionFragmentBinding
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class ForgotPasswordOptionFragment
 *
 * show forgot password options for choosing preferred password resetting method
 */
class ForgotPasswordOptionFragment : BaseFragment() {

    @Inject
    lateinit var forgotPasswordOptionViewModel : ForgotPasswordOptionViewModel

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ForgotPasswordOptionFragmentBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = forgotPasswordOptionViewModel
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
        initLoader()
    }

    override fun initLoader() {

    }

    override fun initViews() {
        setupDrawer()
    }

    override fun setupDrawer() {

    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

}