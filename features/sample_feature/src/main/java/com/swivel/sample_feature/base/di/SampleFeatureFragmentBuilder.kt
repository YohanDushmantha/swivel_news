package com.swivel.sample_feature.base.di

import com.swivel.sample_feature.ui.post.PostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Yohan Dushmantha
 * @class SampleFeatureFragmentBuilder
 *
 * Provide Sample Feature Module related fragments and inject dependencies
 */

@Module
abstract class SampleFeatureFragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindPostFragment() : PostFragment
}