package com.swivel.home.base.di

import androidx.lifecycle.ViewModel
import com.swivel.config.di.annotation.ViewModelKey
import com.swivel.home.ui.home.filtered_news.FilteredNewsViewModel
import com.swivel.home.ui.home.headline_news.HomeViewModel
import com.swivel.home.ui.home.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Yohan Dushmantha
 * @class HomeViewModelBuilder
 *
 * Provide Home Module related viewModels and inject dependencies
 */

@Module
abstract class HomeViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(viewModel: HomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilteredNewsViewModel::class)
    internal abstract fun bindFilteredNewsViewModel(viewModel : FilteredNewsViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    internal abstract fun bindProfileViewModel(viewModel : ProfileViewModel) : ViewModel

}