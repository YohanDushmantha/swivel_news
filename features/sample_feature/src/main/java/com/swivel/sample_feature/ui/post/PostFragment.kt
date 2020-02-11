package com.swivel.sample_feature.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.swivel.core.ui.BaseFragment
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.sample_feature.databinding.PostFragmentBinding
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class PostFragment
 */

class PostFragment : BaseFragment(){

    @Inject
    lateinit var postViewModel: PostViewModel

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return PostFragmentBinding.inflate(inflater,container,false)
            .apply {
                lifecycleOwner = this@PostFragment
                viewModel = postViewModel
                formData = postViewModel.postFormData
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
        Timber.i("YD -> PostFragment -> initObservers")
        initLoader()

    }

    override fun initLoader() {
        pageNavigationId = findNavController().currentDestination?.id
        pageNavigationId?.let { pageNavId ->
            postViewModel.isLoading.observe(this, Observer {
                it?.let {
                    progressBarHandler.toggleProgressUI(router,findNavController(),it,pageNavId)
                }
            })
        }
    }

    override fun initViews() {
        postViewModel.initViewArguments(router.getDeepLinkArguments(DEEP_LINK.POSTS_POSTS))
        setupDrawer()
        postViewModel.fetchPosts()
    }

    override fun setupDrawer() {

    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/
}