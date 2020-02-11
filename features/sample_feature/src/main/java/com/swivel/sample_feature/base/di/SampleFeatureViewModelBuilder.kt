package com.swivel.sample_feature.base.di

import androidx.lifecycle.ViewModel
import com.swivel.config.di.annotation.ViewModelKey
import com.swivel.sample_feature.ui.post.PostViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Yohan Dushmantha
 * @class SampleFeatureViewModelBuilder
 *
 * Provide Sample Feature Module related viewModels and inject dependencies
 */

@Module
abstract class SampleFeatureViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    internal abstract fun bindPostViewModel(viewModel: PostViewModel) : ViewModel
}