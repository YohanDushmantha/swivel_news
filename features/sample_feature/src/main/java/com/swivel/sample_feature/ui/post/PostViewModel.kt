package com.swivel.sample_feature.ui.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import com.swivel.core.ui.BaseViewModel
import com.swivel.models.entities.Post
import com.swivel.models.features.IBaseDeepLinkArguments
import com.swivel.models.features.sample_feature.post.router_arguments.PostDeepLinkArguments
import com.swivel.models.features.shared.confirm_box.enums.ConfirmBoxButtonAppearance
import com.swivel.models.features.shared.confirm_box.event_listeners.ConfirmBoxEventResult
import com.swivel.models.features.shared.confirm_box.router_arguments.ConfirmBoxButtonConfig
import com.swivel.models.features.shared.confirm_box.router_arguments.ConfirmBoxDeepLinkArguments
import com.swivel.models.features.shared.confirm_box.router_arguments.ConfirmBoxViewConfig
import com.swivel.models.features.shared.info_box.enums.InfoBoxAppearance
import com.swivel.models.features.shared.info_box.router_arguments.InfoBoxDeepLinkArguments
import com.swivel.models.features.shared.info_box.router_arguments.InfoBoxViewConfig
import com.swivel.models.libs.navigation.enums.DEEP_LINK
import com.swivel.navigation.router.Router
import com.swivel.repository.post_service_repositories.PostRepository
import com.swivel.repository.exception.DataSourceNotHandledException
import com.swivel.sample_feature.ui.post.enums.PostConfirmBoxID
import com.swivel.sample_feature.ui.post.enums.PostInfoBoxID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class PostViewModel
 */

class PostViewModel @Inject constructor(
    private val router: Router,
    private val postRepository: PostRepository
) : BaseViewModel() {

    val postLiveData = MutableLiveData<MutableList<Post>>()
    var titleLiveData = MutableLiveData<String>()
    @Inject lateinit var postFormData : PostFormData
    @Inject lateinit var postFormValidator: PostFormValidator

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    override fun initViewArguments(deepLinkArguments: IBaseDeepLinkArguments?) {
        (deepLinkArguments as? PostDeepLinkArguments)?.let {
            titleLiveData.postValue(deepLinkArguments.title)
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    fun onTapShowInfoBox(view: View?) {
        view?.let {
            router.route(findNavController(view), DEEP_LINK.SHARED_INFO_BOX, null,
                InfoBoxDeepLinkArguments().apply {
                    infoBoxId = PostInfoBoxID.INFO_INFO_BOX.infoBoxID
                    title = "Info Title"
                    detail = "Lorem ipsum dolor sit amet"
                })
        }
    }

    fun onTapShowSuccessBox(view: View?) {
        view?.let {
            router.route(findNavController(view), DEEP_LINK.SHARED_INFO_BOX, null,
                InfoBoxDeepLinkArguments().apply {
                    infoBoxId = PostInfoBoxID.SUCCESS_INFO_BOX.infoBoxID
                    title = "Info Title"
                    detail =
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat"
                    viewConfig = InfoBoxViewConfig()
                        .apply {
                        infoBoxType = InfoBoxAppearance.SUCCESS
                        onTapInfoBoxCallback = onTapInfoBox
                    }
                })
        }
    }

    fun onTapShowWarningBox(view: View?) {
        view?.let {
            router.route(findNavController(view), DEEP_LINK.SHARED_INFO_BOX, null,
                InfoBoxDeepLinkArguments().apply {
                    infoBoxId = PostInfoBoxID.WARNING_INFO_BOX.infoBoxID
                    title = "Info Title"
                    detail =
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud"
                    viewConfig = InfoBoxViewConfig()
                        .apply {
                        infoBoxType = InfoBoxAppearance.WARNING
                    }
                })
        }
    }

    fun onTapShowErrorBox(view: View?) {
        view?.let {
            router.route(findNavController(view), DEEP_LINK.SHARED_INFO_BOX, null,
                InfoBoxDeepLinkArguments().apply {
                    infoBoxId = PostInfoBoxID.ERROR_INFO_BOX.infoBoxID
                    title = "Info Title"
                    detail =
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                    viewConfig = InfoBoxViewConfig()
                        .apply {
                        infoBoxType = InfoBoxAppearance.ERROR
                    }
                })
        }
    }

    fun onTapShowConfirmBox(view: View?) {
        view?.let {
            router.route(findNavController(view), DEEP_LINK.SHARED_CONFIRM_BOX, null,
                ConfirmBoxDeepLinkArguments().apply {
                    confirmBoxId = PostConfirmBoxID.SAMPLE_CONFIRM_MESSAGE_1.confirmBoxID
                    title = "Are You Sure You Want to Cancel?"
                    detail = "You may incur a partial charge for this trip since it's already in progress."

                    viewConfig = ConfirmBoxViewConfig()
                        .apply {
                        val buttonConfiglist : HashMap<Int, ConfirmBoxButtonConfig> = HashMap()

                        buttonConfiglist.put(
                            ConfirmBoxButtonAppearance.POSITIVE.appearanceID,
                            ConfirmBoxButtonConfig(
                                onTapConfirmBox
                            )
                        )

                        buttonConfiglist.put(
                            ConfirmBoxButtonAppearance.NEGATIVE.appearanceID,
                            ConfirmBoxButtonConfig(
                                onTapConfirmBox
                            ).apply {
                            buttonText  = "NO"
                        })

                        buttonList = buttonConfiglist
                    }
                })
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * EVENTS HANDLING - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * DATA HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    fun insertPost() {
        Timber.i("YD -> PostViewModel -> insertPosts")

        viewModelScope.launch {
            try {

            } catch (ex: DataSourceNotHandledException) {
                Timber.i("YD -> PostViewModel -> insertPost need to show ${ex.message}")
                Timber.e(ex)
            }
        }
    }

    fun fetchPosts() {
        Timber.i("YD -> PostViewModel -> fetchPosts")

        viewModelScope.launch {
            try {
                isLoading.postValue(true)
                Timber.i("YD -> PostViewModel -> fetchPosts show Loader")
                val posts = withContext(Dispatchers.IO) {
                    postRepository.fetchPosts()
                }

                postLiveData.value = posts
                Timber.i("YD -> PostViewModel -> fetchPosts Count ${postLiveData.value?.size}")
            } catch (ex: DataSourceNotHandledException) {
                Timber.i("YD -> PostViewModel -> fetchPosts need to show ${ex.message}")
                Timber.e(ex)
            } finally {
                isLoading.postValue(false)
                Timber.i("YD -> PostViewModel -> fetchPosts hide Loader")
            }
        }

    }

    /**---------------------------------------------------------------------------------------------*
     * DATA HANDLING - END
     *----------------------------------------------------------------------------------------------*/


    /**---------------------------------------------------------------------------------------------*
     * INFO BOX EVENT HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    private val onTapInfoBox : (infoBoxId: Short) -> Unit =  {
        Timber.i("YD -> OnTapInfoBox $it")

        when(it){
            PostInfoBoxID.SUCCESS_INFO_BOX.infoBoxID -> {
                Timber.i("YD -> OnTapInfoBox Success Info Box Clicked")
            }
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * INFO BOX EVENT HANDLING - END
     *----------------------------------------------------------------------------------------------*/


    /**---------------------------------------------------------------------------------------------*
     * CONFIRM BOX EVENT HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    private val onTapConfirmBox : ((
        confirmBoxID : Short,
        confirmBoxButtonAppearance : ConfirmBoxButtonAppearance
        ,result : ConfirmBoxEventResult?
    ) -> Unit) = { confirmBoxID, confirmBoxButtonAppearance, result ->

        Timber.i("YD -> OnTapConfirmBox -> $confirmBoxID")
        when(confirmBoxID){
            PostConfirmBoxID.SAMPLE_CONFIRM_MESSAGE_1.confirmBoxID -> {
                handleSampleConfirmMessage1(confirmBoxButtonAppearance)
            }
        }


    }

    private fun handleSampleConfirmMessage1(confirmBoxButtonAppearance : ConfirmBoxButtonAppearance){
        when(confirmBoxButtonAppearance){
            ConfirmBoxButtonAppearance.POSITIVE -> {
                Timber.i("YD -> OnTapConfirmBox -> Positive Button Clicked")
            }
            ConfirmBoxButtonAppearance.NEGATIVE -> {
                Timber.i("YD -> OnTapConfirmBox -> Negative Button Clicked")
            }
        }
    }


    /**---------------------------------------------------------------------------------------------*
     * CONFIRM BOX EVENT HANDLING - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * FORM DATA HANDLING - START
     *----------------------------------------------------------------------------------------------*/

    fun onPostDataSubmit(view : View?){
        //postFormValidator.valida
        Timber.i("YD -> Post Form Data FirstName : ${postFormData.firstName} , LastName : ${postFormData.lastName} ")
        //postFormData.lastName = "Dushmantha"
        postFormValidator.validateFormData(postFormData).let {
            Timber.i("YD -> Post Form Data isValid = ${it?.isValid}")
            Timber.i("YD -> Post Form Data message = ${it?.message}")
        }



    }


    /**---------------------------------------------------------------------------------------------*
     * FORM DATA HANDLING - END
     *----------------------------------------------------------------------------------------------*/
}