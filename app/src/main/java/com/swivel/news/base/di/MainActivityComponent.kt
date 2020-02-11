package com.swivel.news.base.di

import com.swivel.news.main.MainActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

/**
 * @author Yohan Dushmantha
 * @class MainActivityComponent
 *
 * MainActivityComponent for Injecting MainActivity Dependencies
 */

@Subcomponent
interface MainActivityComponent : AndroidInjector<MainActivity>{

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

}