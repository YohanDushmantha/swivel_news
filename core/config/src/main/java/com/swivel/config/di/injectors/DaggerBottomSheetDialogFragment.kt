package com.swivel.config.di.injectors

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author Yohan Dushmantha
 * @class DaggerBottomSheetDialogFragment
 *
 * dagger don't provide dependency injection for bottom sheet dialog fragments, therefore we
 * should inject bottom sheet dependencies manually
 *
 * this class handle the dependency injection for bottom sheet dialog fragments
 */
open class DaggerBottomSheetDialogFragment : BottomSheetDialogFragment(),
    HasSupportFragmentInjector {

    @Inject
    lateinit var mChildFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mChildFragmentInjector
    }
}