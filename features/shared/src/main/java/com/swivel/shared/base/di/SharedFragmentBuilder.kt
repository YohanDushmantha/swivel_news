package com.swivel.shared.base.di

import com.swivel.shared.ui.confirm_box.ConfirmBox
import com.swivel.shared.ui.info_box.InfoBox
import com.swivel.shared.ui.progress_bar.ProgressDialogFragment
import com.swivel.shared.ui.verify_mobile_number.VerifyMobileNumberFragment
import com.swivel.shared.ui.verify_otp.VerifyOtpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Yohan Dushmantha
 * @class SharedFragmentBuilder
 *
 * Provide Shared Module related fragments and inject dependencies
 */

@Module
abstract class SharedFragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindVerifyOtpFragment() : VerifyOtpFragment

    @ContributesAndroidInjector
    internal abstract fun bindProgressBarFragment() : ProgressDialogFragment

    @ContributesAndroidInjector
    internal abstract fun bindInfoBoxFragment() : InfoBox

    @ContributesAndroidInjector
    internal abstract fun bindConfirmBoxBottomSheetFragment() : ConfirmBox

    @ContributesAndroidInjector
    internal abstract fun bindVerifyMobileNumberFragment() : VerifyMobileNumberFragment

}