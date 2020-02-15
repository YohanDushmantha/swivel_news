package com.swivel.home.ui.home.filtered_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.swivel.core.ui.BaseFragment
import com.swivel.home.R
import com.swivel.home.databinding.FilteredNewsFragmentBinding
import com.swivel.home.ui.home.NewsAdaptor
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.models.libs.navigation.enums.DrawerConfigSettings
import kotlinx.android.synthetic.main.filtered_news_fragment.*
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
    private val newsAdaptor : NewsAdaptor = NewsAdaptor()
    private var isFABOpen : Boolean = false

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
        initializeNewsListUpdateListner()
        initNewsList()
        initFilter()
    }

    private fun initializeNewsListUpdateListner(){
        filteredNewsViewModel.newsList.observe(this, Observer {
            newsAdaptor.submitList(it)
        })
    }

    private fun initNewsList(){
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = newsAdaptor
    }

    /**=============================================================================================*
     * NAVIGATION DRAWER - START
     *==============================================================================================*/

    override fun setupDrawer() {
        drawerHandler.setup(
            findNavController(),
            DrawerConfigSettings.REMOVE_ALL
        )
    }

    private fun setupBottomNavigationBar(){
        NavigationUI.setupWithNavController(filteredNewsFragmentBinding.bttmNav,findNavController())
    }

    /**=============================================================================================*
     * NAVIGATION DRAWER - END
     *==============================================================================================*/

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * SETUP FILTER ICON - START
     *----------------------------------------------------------------------------------------------*/

    private fun initFilter(){
        filterIcon.setOnClickListener(View.OnClickListener {
            if (!isFABOpen) {
                showFABMenu()
            } else {
                closeFABMenu()
            }
        })

        //close fab menu items when clicked.
        filteredNewsViewModel.shouldCloseFabItem?.observe(this, Observer {
            closeFABMenu()
        })
    }

    /**
     * show fab menu items
     */
    private fun showFABMenu() {
        isFABOpen = true
        showFab(animal,animalLabel,R.dimen.standard_55)
        showFab(earthQuarke,earthQuakeLabel,R.dimen.standard_105)
        showFab(apple,appleLabel,R.dimen.standard_155)
        showFab(bitCoin,bitCoinLabel,R.dimen.standard_205)
    }

    /**
     * show fab icon
     * @param fabIcon fab icon
     * @param fabLabel label of fab icon
     * @param dimention movement of the components
     */
    private fun showFab(fabIcon : FloatingActionButton?,fabLabel : TextView?,dimention : Int){
        fabIcon?.animate()?.translationY(-resources.getDimension(dimention))
        fabLabel?.visibility = View.VISIBLE
        fabLabel?.animate()?.translationY(-resources.getDimension(dimention))
    }

    /**
     * close fab menu items
     */
    private fun closeFABMenu() {
        isFABOpen = false
        closeFab(animal,animalLabel)
        closeFab(earthQuarke,earthQuakeLabel)
        closeFab(apple,appleLabel)
        closeFab(bitCoin,bitCoinLabel)
    }

    /**
     * close fab menu item
     * @param fabIcon fab icon
     * @param fabLabel label of fab icon
     */
    private fun closeFab(fabIcon: FloatingActionButton?,fabLabel: TextView?){
        fabIcon?.animate()?.translationY(0f)
        fabLabel?.animate()?.translationY(0f)
        fabLabel?.visibility = View.GONE
    }

    /**---------------------------------------------------------------------------------------------*
     * SETUP FILTER ICON - END
     *----------------------------------------------------------------------------------------------*/


}