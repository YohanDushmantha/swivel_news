package com.swivel.home.ui.filtered_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.swivel.core.ui.BaseFragment
import com.swivel.home.databinding.FilteredNewsFragmentBinding
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class FilteredNewsFragment
 *
 * filtered news fragment provides capability to check news of selected category
 */
class FilteredNewsFragment : BaseFragment() {
    @Inject
    lateinit var filteredNewsViewModel: FilteredNewsViewModel

    lateinit var filteredNewsFragmentBinding: FilteredNewsFragmentBinding

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        filteredNewsFragmentBinding =  FilteredNewsFragmentBinding.inflate(inflater,container,false)
            .apply {
                lifecycleOwner = this@FilteredNewsFragment
                viewModel = filteredNewsViewModel
            }
        return filteredNewsFragmentBinding.root
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
            filteredNewsViewModel.isLoading.observe(viewLifecycleOwner, Observer {
                progressBarHandler.toggleProgressUI(router,findNavController(),it,pageNavId)
            })
        }
    }

    override fun initViews() {
        filteredNewsViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.LOGIN))
        setupDrawer()
        setupBottomNavigationBar()
    }

    override fun setupDrawer() {

    }

    private fun setupBottomNavigationBar(){
        NavigationUI.setupWithNavController(filteredNewsFragmentBinding.bttmNav,findNavController())
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/
}