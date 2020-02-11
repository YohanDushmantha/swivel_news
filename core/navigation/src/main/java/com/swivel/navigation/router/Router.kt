package com.swivel.navigation.router

import android.net.Uri
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.deep_link_router.DeepLinkRouter
import com.swivel.navigation.router.navigation_router.NavigationRouter
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class Router
 *
 * Handle routing inside the application
 */
class Router @Inject constructor(
    val deepLinkRouter: DeepLinkRouter,
    val navigationRouter: NavigationRouter
) : IRouter {

    var deepLinkArgumentsMap = HashMap<String, IBaseDeepLinkArguments>()

    fun config(navController: NavController){

    }

    /**
     * route to destination using default way of navigation ui
     * @param navController NavigationController
     * @param action NavDirectioons instance. this is the destination of the navigation
     */
    override fun route(navController: NavController, action: NavDirections) {
        if (navController.currentDestination?.getAction(action.actionId) != null){
            navController.navigate(action)
            Timber.i("YD -> NavController action != null")
        }else{
            Timber.i("YD -> NavController action == null")
        }

    }

    /**
     * get DeepLinkArguments that is received through navigation
     * @param deepLink DEEP_LINK enum that is related to destination of navigation
     * @return IBaseDeepLinkArguments return DeepLinkArguments this Arguments object consists of
     * data that need to load deep link navigation properly
     */
    override fun getDeepLinkArguments(deepLink: DEEP_LINK): IBaseDeepLinkArguments? {
        return deepLinkArgumentsMap[deepLink.KEY]
    }

    /**
     * route to destination using deep link navigation without DeepLinkArguments
     * @param navController NavigationController
     * @param deepLink DEEP_LINK enum that is related to destination of navigation
     * @param urlArgs Argument list for deep link uri. using urlArgs, can change the uri dynamically
     * destination also will be changed according the dynamic uri
     */
    override fun route(navController: NavController, deepLink: DEEP_LINK, urlArgs: Array<String>?) {
        navController.navigate(bindURLArgs(deepLink, urlArgs))
    }

    /**
     * route to destination using deep link navigation with DeepLinkArguments
     * @param navController NavigationController
     * @param deepLink DEEP_LINK enum that is related to destination of navigation
     * @param urlArgs Argument list for deep link uri. using urlArgs, can change the uri dynamically
     * destination also will be changed according the dynamic uri
     * @param deepLinkArguments for passing data in between destinations when navigate through
     * deep link uri
     */
    override fun route(
        navController: NavController,
        deepLink: DEEP_LINK,
        urlArgs: Array<String>?,
        deepLinkArguments: IBaseDeepLinkArguments?
    ) {
        deepLinkArguments?.let {
            deepLinkArgumentsMap.put(deepLink.KEY, deepLinkArguments)
        }
        route(navController, deepLink, urlArgs)
    }

    /**
     * route to previous destination
     * @param navController NavigationController
     */
    override fun routeBack(navController: NavController) {
        navController.navigateUp()
    }

    /**
     * close dialog fragment
     * there is no proper way to close dialog fragments after loading them through deep linking
     * this method provides capability to close dialog fragments but you should have permission for
     * accessing fragment id of the fragment which is need to dismiss
     *
     * @param navController Navigation Controller
     * @param fragmentIdForPopup fragment id which is need to dismiss
     * @param inclusive
     */
    override fun dismissDialogFragment(
        navController: NavController?,
        fragmentIdForPopup : Int,
        inclusive: Boolean
    ){
        Timber.i("YC -> PAGE NAVIGATION ID -> HIDE -> Dismiss Dialog Fragment 1")
        navController?.currentDestination?.let {
            Timber.i("YC -> PAGE NAVIGATION ID -> HIDE -> Dismiss Dialog Fragment 2 ${it.id} , $fragmentIdForPopup")
            /*if(it.id == fragmentIdForPopup){
                Timber.i("YC -> PAGE NAVIGATION ID -> HIDE -> Dismiss Dialog Fragment 3 navController id : ${it.id} , $fragmentIdForPopup")
                navController
                    .popBackStack(it.id,inclusive)
            }*/
            popBackStack(navController,fragmentIdForPopup,false)
        }
    }

    /**
     * pop backstack until provided destination
     *
     * @param navController Navigation Controller
     * @param destination destination point of stopping dismiss process
     * @param inclusive if inclusive is true, dismiss back stack including provided destination else
     * keep provided destination
     */
    override fun popBackStack(
        navController: NavController?,
        destination: Int?,
        inclusive: Boolean
    ) {
        destination?.let {
            navController?.popBackStack(destination,inclusive)
        } ?: navController?.popBackStack()
    }

    /**
     * bind url arguments to the deep link uri
     * @param deepLink DEEP_LINK enum that is related to destination of navigation
     * @param args Argument list for deep link uri. using urlArgs, can change the uri dynamically
     * destination also will be changed according the dynamic uri
     * @return Uri crated uri using passed arguments
     */
    private fun bindURLArgs(deepLink: DEEP_LINK, args: Array<String>?): Uri {
        var baseURL: String = deepLink.URL
        try {
            args?.forEachIndexed { index, s ->
                baseURL += "/$s"
                if (args.size - 1 == index) {
                    baseURL += "/"
                }
            }
        } catch (ex: Exception) {
            Timber.e(ex.message)
        } finally {
            Timber.i("baseURl : $baseURL")
            return baseURL.toUri()
        }
    }

}