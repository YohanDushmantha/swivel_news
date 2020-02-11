package com.swivel.navigation.router

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.libs.navigation.enums.DEEP_LINK


/**
 * @author Yohan Dushmantha
 * @class IRouter
 *
 * provide an interface for Router Object
 */
interface IRouter {



    /**
     * route to destination using default way of navigation ui
     * @param navController NavigationController
     * @param action NavDirectioons instance. this is the destination of the navigation
     */
    fun route(navController : NavController, action : NavDirections)

    /**
     * get DeepLinkArguments that is received through navigation
     * @param deepLink DEEP_LINK enum that is related to destination of navigation
     * @return IBaseDeepLinkArguments return DeepLinkArguments this Arguments object consists of
     * data that need to load deep link navigation properly
     */
    fun getDeepLinkArguments(deepLink: DEEP_LINK) : IBaseDeepLinkArguments?

    /**
     * route to destination using deep link navigation without DeepLinkArguments
     * @param navController NavigationController
     * @param deepLink DEEP_LINK enum that is related to destination of navigation
     * @param urlArgs Argument list for deep link uri. using urlArgs, can change the uri dynamically
     * destination also will be changed according the dynamic uri
     */
    fun route(navController: NavController, deepLink: DEEP_LINK, urlArgs : Array<String>? = null)

    /**
     * route to destination using deep link navigation with DeepLinkArguments
     * @param navController NavigationController
     * @param deepLink DEEP_LINK enum that is related to destination of navigation
     * @param urlArgs Argument list for deep link uri. using urlArgs, can change the uri dynamically
     * destination also will be changed according the dynamic uri
     * @param deepLinkArguments for passing data in between destinations when navigate through
     * deep link uri
     */
    fun route(navController: NavController, deepLink: DEEP_LINK, urlArgs: Array<String>?, deepLinkArguments: IBaseDeepLinkArguments?)

    /**
     * route to previous destination
     * @param navController NavigationController
     */
    fun routeBack(navController: NavController)

    /**
     * close dialog fragment
     * there is no proper way to close dialog fragments after loading them through deep linking
     * this method provides capability to close dialog fragments but you should have permission for
     * accessing fragment id of the fragment which is need to dismiss
     *
     * @param navController Navigation Controller
     * @param fragmentIdForPopup fragment id which is need to dismiss
     * @param inclusive status for removing required fragment too
     */
    fun dismissDialogFragment(navController: NavController?, fragmentIdForPopup : Int, inclusive: Boolean = true)

    /**
     * pop backstack until provided destination
     *
     * @param navController Navigation Controller
     * @param destination destination point of stopping dismiss process
     * @param inclusive if inclusive is true, dismiss back stack including provided destination else
     * keep provided destination
     */
    fun popBackStack(navController: NavController?, destination: Int? = null, inclusive : Boolean = false)
}