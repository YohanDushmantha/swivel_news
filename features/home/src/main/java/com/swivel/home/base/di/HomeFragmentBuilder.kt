package com.swivel.home.base.di

import com.swivel.home.ui.home.filtered_news.FilteredNewsFragment
import com.swivel.home.ui.home.headline_news.HomeFragment
import com.swivel.home.ui.home.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * @author Yohan Dushmantha
 * @class HomeFragmentBuilder
 *
 * Provide Home Module related fragments and inject dependencies
 */

@Module
abstract class HomeFragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindHomeFragment() : HomeFragment

    @ContributesAndroidInjector
    internal abstract fun bindFilteredNewsFragment() : FilteredNewsFragment

    @ContributesAndroidInjector
    internal abstract fun bindProfileFragment() : ProfileFragment

}