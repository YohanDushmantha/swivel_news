package com.swivel.news

import androidx.lifecycle.ViewModel
import com.swivel.config.di.annotation.ViewModelKey
import com.swivel.news.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Yohan Dushmantha
 * @class AppViewModelBuilder
 *
 * Provide app Module related viewModels and inject dependencies
 */

@Module
abstract class AppViewModelBuilder{

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun MainAppViewModel(viewModel: MainViewModel) : ViewModel
}