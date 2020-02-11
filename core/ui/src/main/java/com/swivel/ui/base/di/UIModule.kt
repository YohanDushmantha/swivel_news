package com.swivel.ui.base.di

import android.content.Context
import com.swivel.ui.base.SampleUI
import com.swivel.ui.base.helpers.animation_handler.bounce_handler.BounceAnimationHandler
import com.swivel.ui.base.helpers.animation_handler.bounce_handler.IBounceAnimationHandler
import com.swivel.ui.base.helpers.animation_handler.rotation_handler.IRotateAnimationHandler
import com.swivel.ui.base.helpers.animation_handler.rotation_handler.RotateAnimationHandler
import com.swivel.ui.base.helpers.back_handler.BackHandler
import com.swivel.ui.base.helpers.back_handler.IBackHandler
import com.swivel.ui.base.helpers.custom_dots_progress_bar_animator.CustomDotsProgressBarAnimator
import com.swivel.ui.base.helpers.custom_dots_progress_bar_animator.ICustomDotsProgressBarAnimator
import com.swivel.ui.base.helpers.drawer_handler.DrawerHandler
import com.swivel.ui.base.helpers.drawer_handler.IDrawerHandler
import com.swivel.ui.base.helpers.info_box_handler.IInfoBoxHandler
import com.swivel.ui.base.helpers.info_box_handler.InfoBoxHandler
import com.swivel.ui.base.helpers.progress_bar_handler.IProgressBarHandler
import com.swivel.ui.base.helpers.progress_bar_handler.ProgressBarHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class UIModule
 *
 * Provides ui module related dependencies for dagger
 */

@Module
class UIModule {

    @Provides
    fun getSampleUI() : SampleUI{
        return SampleUI()
    }

    @Provides
    fun providesInfoBoxHandler() : IInfoBoxHandler{
        return InfoBoxHandler()
    }

    @Provides
    @Singleton
    fun provideDrawerHandler(context: Context) : IDrawerHandler{
        return DrawerHandler(context)
    }

    @Provides
    fun provideBackHandler() : IBackHandler{
        return BackHandler()
    }

    @Provides
    @Singleton
    fun provideProgressBarHandler (context: Context) : IProgressBarHandler {
        return ProgressBarHandler(context)
    }

    /**---------------------------------------------------------------------------------------------*
     * ANIMATION HANDLER - START
     *----------------------------------------------------------------------------------------------*/

    @Provides
    @Singleton
    fun provideBounceAnimationHandler (context: Context) : IBounceAnimationHandler{
        return BounceAnimationHandler(context)
    }

    @Provides
    @Singleton
    fun provideRotateAnimationHandler(context: Context) : IRotateAnimationHandler{
        return RotateAnimationHandler(context)
    }

    @Provides
    @Singleton
    fun provideCustomDotsProgressAnimator(context: Context) : ICustomDotsProgressBarAnimator{
        return CustomDotsProgressBarAnimator(context)
    }

    /**---------------------------------------------------------------------------------------------*
     * ANIMATION HANDLER - END
     *----------------------------------------------------------------------------------------------*/

}