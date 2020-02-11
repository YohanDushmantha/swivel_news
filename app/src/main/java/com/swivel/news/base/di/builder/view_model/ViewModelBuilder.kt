package com.swivel.news.base.di.builder.view_model

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * @author Yohan Dushmantha
 * @class ViewModelBuilder
 *
 * MainViewModel Builder for creating viewModels from ViewModelFactory
 */
@Module
abstract class ViewModelBuilder {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

}