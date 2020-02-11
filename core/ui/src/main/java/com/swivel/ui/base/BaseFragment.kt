package com.swivel.core.ui

import android.app.Activity
import com.swivel.navigation.router.Router
import com.swivel.ui.base.DummyInjectableField
import com.swivel.ui.base.helpers.drawer_handler.IDrawerHandler
import com.swivel.ui.base.helpers.progress_bar_handler.IProgressBarHandler
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class BaseFragment
 *
 * BaseFragment should be implemented by every fragment
 *
 * All kind of common methods which is related to fragments should be added here
 *
 * provides abstract methods for fragments to protect consistency, readability , and maintainability
 * of the code
 *
 */
abstract class BaseFragment : DaggerFragment() {

    @Inject lateinit var dummy: DummyInjectableField

    @Inject lateinit var drawerHandler: IDrawerHandler
    @Inject lateinit var router: Router
    @Inject lateinit var progressBarHandler: IProgressBarHandler

    var pageNavigationId : Int? = null


    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - END
     *----------------------------------------------------------------------------------------------*/


    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * all observers should be implemented here
     */
    abstract fun initObservers()

    /**
     * progress bar should be implemented here
     */
    abstract fun initLoader()

    /**
     * progress bar should be implemented here
     */
    abstract fun initViews()

    /**
     * setting up drawer and appbar
     * if any fragment need to setup specific behaviour for drawer and app bar, setupDrawer should be
     * override inside target fragment and should invoke setup method of drawer handler
     */
    abstract fun setupDrawer()

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * APPLICATION RELATED MAJOR METHODS - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * close application and remove current tasks by using provided activity
     * @param activity current activity which is holds fragments
     */
    fun closeApp(activity: Activity?){
        try {
            activity?.finishAndRemoveTask()
        }catch (ex : Exception){
            Timber.e(ex)
        }
    }

}