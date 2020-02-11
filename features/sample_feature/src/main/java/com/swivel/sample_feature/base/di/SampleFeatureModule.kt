package com.swivel.sample_feature.base.di

import android.content.Context
import com.swivel.sample_feature.ui.post.PostFormData
import com.swivel.sample_feature.ui.post.PostFormValidator
import com.swivel.validator.BaseValidator
import dagger.Module
import dagger.Provides

/**
 * @author Yohan Dushmantha
 * @class SampleFeatureModule
 *
 * Provides Sample Feature module related dependencies for dagger
 */
@Module
class SampleFeatureModule {

    /**---------------------------------------------------------------------------------------------*
     * POST - START
     *----------------------------------------------------------------------------------------------*/

    @Provides
    fun providePostFormData() : PostFormData{
        return PostFormData()
    }

    @Provides
    fun providePostFormValidator(context : Context, baseValidator: BaseValidator) : PostFormValidator{
        return PostFormValidator(context,baseValidator)
    }


    /**---------------------------------------------------------------------------------------------*
     * POST - END
     *----------------------------------------------------------------------------------------------*/
}