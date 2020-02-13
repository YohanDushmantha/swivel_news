package com.swivel.home.base.di

import com.swivel.home.ui.filtered_news.FilteredNewsFragment
import com.swivel.home.ui.home.HomeFragment
import com.swivel.home.ui.profile.ProfileFragment
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