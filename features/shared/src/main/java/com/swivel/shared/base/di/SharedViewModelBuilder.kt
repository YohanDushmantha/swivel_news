package com.swivel.shared.base.di

import androidx.lifecycle.ViewModel
import com.swivel.config.di.annotation.ViewModelKey
import com.swivel.shared.ui.confirm_box.ConfirmBoxViewModel
import com.swivel.shared.ui.info_box.InfoBoxViewModel
import com.swivel.shared.ui.progress_bar.ProgressDialogViewModel
import com.swivel.shared.ui.verify_mobile_number.VerifyMobileNumberViewModel
import com.swivel.shared.ui.verify_otp.VerifyOtpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Yohan Dushmantha
 * @class SharedViewModelBuilder
 *
 * Provide Shared Module related viewModels and inject dependencies
 */

@Module
abstract class SharedViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(VerifyOtpViewModel::class)
    internal abstract fun bindVerifyOtpViewModel(viewModel: VerifyOtpViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProgressDialogViewModel::class)
    internal abstract fun bindProgressDialogViewModel (viewModel: ProgressDialogViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InfoBoxViewModel::class)
    internal abstract fun bindInfoBoxViewModel(viewModel: InfoBoxViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ConfirmBoxViewModel::class)
    internal abstract fun bindConfirmBoxViewModel(viewModel : ConfirmBoxViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VerifyMobileNumberViewModel::class)
    internal abstract fun bindVerifyMobileNumberViewModel(viewModel : VerifyMobileNumberViewModel) : ViewModel
}