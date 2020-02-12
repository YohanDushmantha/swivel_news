package com.swivel.registration.ui.user_registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.swivel.core.ui.BaseFragment
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.registration.databinding.UserRegistrationFragmentBinding
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class UserRegistrationFragment
 *
 * user registration fragment provides capability to gather basic user details and save gathered
 * data into local storage
 */
class UserRegistrationFragment : BaseFragment(){

    @Inject
    lateinit var userRegistrationViewModel: UserRegistrationViewModel

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UserRegistrationFragmentBinding.inflate(inflater,container,false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = userRegistrationViewModel
                formData = userRegistrationViewModel.userRegistrationFormData
            }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    override fun onResume() {
        super.onResume()
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
            userRegistrationViewModel.isLoading.observe(viewLifecycleOwner, Observer {
                progressBarHandler.toggleProgressUI(router,findNavController(),it,pageNavId)
            })
        }
    }

    override fun initViews() {
        userRegistrationViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.USER_REGISTRATION))
        setupDrawer()
    }

    override fun setupDrawer() {

    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/
}