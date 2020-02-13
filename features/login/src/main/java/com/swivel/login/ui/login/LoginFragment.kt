package com.swivel.login.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.swivel.core.ui.BaseFragment
import com.swivel.login.databinding.LoginFragmentBinding
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class LoginFragment
 *
 * login fragment provides capability to log into swivel news application using locally stored
 * credentials
 */
class LoginFragment : BaseFragment() {
    @Inject
    lateinit var loginViewModel: LoginViewModel

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LoginFragmentBinding.inflate(inflater,container,false)
            .apply {
                lifecycleOwner = this@LoginFragment
                viewModel = loginViewModel
                formData = loginViewModel.loginFormData
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
        pageNavigationId = findNavController().currentDestination?.id

        pageNavigationId?.let { pageNavId ->
            loginViewModel.isLoading.observe(viewLifecycleOwner, Observer {
                progressBarHandler.toggleProgressUI(router,findNavController(),it,pageNavId)
            })
        }
    }

    override fun initViews() {
        loginViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.LOGIN))
        setupDrawer()
    }

    override fun setupDrawer() {

    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/
}