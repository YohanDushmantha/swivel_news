package com.swivel.home.ui.home.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.swivel.core.ui.BaseFragment
import com.swivel.home.databinding.ProfileFragmentBinding
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class ProfileFragment
 *
 * profile fragment provides capability to check profile details of current user
 */
class ProfileFragment : BaseFragment() {
    @Inject
    lateinit var profileViewModel: ProfileViewModel

    lateinit var profileFragmentBinding : ProfileFragmentBinding

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         profileFragmentBinding = ProfileFragmentBinding.inflate(inflater,container,false)
            .apply {
                lifecycleOwner = this@ProfileFragment
                viewModel = profileViewModel
            }
         return profileFragmentBinding.root
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
            profileViewModel.isLoading.observe(viewLifecycleOwner, Observer {
                progressBarHandler.toggleProgressUI(router,findNavController(),it,pageNavId)
            })
        }
    }

    override fun initViews() {
        profileViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.LOGIN))
        setupDrawer()
        setupBottomNavigationBar()
    }

    override fun setupDrawer() {

    }

    private fun setupBottomNavigationBar(){
        NavigationUI.setupWithNavController(profileFragmentBinding.bttmNav,findNavController())
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/
}