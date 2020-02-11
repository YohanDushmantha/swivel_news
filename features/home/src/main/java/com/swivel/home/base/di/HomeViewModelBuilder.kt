package com.swivel.home.base.di

import androidx.lifecycle.ViewModel
import com.swivel.config.di.annotation.ViewModelKey
import com.swivel.home.ui.home.HomeViewModel
import com.swivel.home.ui.trip_planner.TripPlannerViewModal
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
    @ViewModelKey(TripPlannerViewModal::class)
    internal abstract fun bindTripPlannerViewModel(viewModel : TripPlannerViewModal) : ViewModel

}