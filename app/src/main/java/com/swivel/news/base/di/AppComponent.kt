package com.swivel.news.base.di

import android.app.Application
import com.swivel.amazon.base.di.S3Module
import com.swivel.config.di.ConfigModule
import com.swivel.crypto.base.di.CryptoModule
import com.swivel.firebase.base.di.FirebaseAuthModule
import com.swivel.firebase.base.di.FirebaseDatabaseModule
import com.swivel.home.base.di.HomeModule
import com.swivel.local.base.di.RoomModule
import com.swivel.login.base.di.LoginModule
import com.swivel.models.base.di.ModelsModule
import com.swivel.navigation.base.di.NavigationModule
import com.swivel.onboarding.base.di.OnboardingModule
import com.swivel.profile.base.di.ProfileModule
import com.swivel.registration.base.di.RegistrationModule
import com.swivel.remote.base.di.NewsServiceApiModule
import com.swivel.remote.base.di.MapServiceApiModule
import com.swivel.remote.base.di.NetworkModule
import com.swivel.repository.base.di.RepositoryModule
import com.swivel.sample_feature.base.di.SampleFeatureModule
import com.swivel.security.base.di.SecurityModule
import com.swivel.shared.base.di.SharedModule
import com.swivel.shared_pref.base.di.SharedPrefModule
import com.swivel.ui.base.di.UIModule
import com.swivel.utility.base.di.UtilityModule
import com.swivel.validator.base.di.ValidatorModule
import com.swivel.news.base.SwivelNewsApplication
import com.swivel.news.base.di.builder.ActivityBuilder
import com.swivel.news.base.di.builder.view_model.ViewModelBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author Yohan Dushmantha
 * @class AppComponent
 *
 * Main AppComponent of the Application
 */
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelBuilder::class,
        ActivityBuilder::class,

        /**------------------------------------------------------------------------------------------------------------*
         * CORE MODULES - START
         *-------------------------------------------------------------------------------------------------------------*/

        //Navigation Module
        NavigationModule::class,

        //UI Module
        UIModule::class,

        //Config Module
        ConfigModule::class,

        //Security Module
        SecurityModule::class,

        //Crypto Module
        CryptoModule::class,

        //Utility Module
        UtilityModule::class,

        //Validator Module
        ValidatorModule::class,

        /**------------------------------------------------------------------------------------------------------------*
         * CORE MODULES - END
         *-------------------------------------------------------------------------------------------------------------*/

        /**------------------------------------------------------------------------------------------------------------*
         * DATA MODULES - START
         *-------------------------------------------------------------------------------------------------------------*/

        //Repository MODULE
        RepositoryModule::class,

        //Remote Module
        NetworkModule::class,
        NewsServiceApiModule::class,
        MapServiceApiModule::class,

        //Local Module
        RoomModule::class,

        //Firebase Module
        FirebaseDatabaseModule::class,
        FirebaseAuthModule::class,

        //Amazon Module
        S3Module::class,

        //Shared Preferences Module
        SharedPrefModule::class,

        //Models Module
        ModelsModule::class,

        /**------------------------------------------------------------------------------------------------------------*
         * DATA MODULES - END
         *-------------------------------------------------------------------------------------------------------------*/

        /**------------------------------------------------------------------------------------------------------------*
         * FEATURE MODULES - END
         *-------------------------------------------------------------------------------------------------------------*/



        // Onboarding Module
        OnboardingModule::class,

        // Login Module
        LoginModule::class,

        // RegistrationModule
        RegistrationModule::class,

        //Home Module
        HomeModule::class,

        //Profile Module
        ProfileModule::class,

        //Shared Module
        SharedModule::class,

        //Sample Feature Module
        SampleFeatureModule::class

        /**------------------------------------------------------------------------------------------------------------*
         * FEATURE MODULES - END
         *-------------------------------------------------------------------------------------------------------------*/



    ]
)

@Singleton
interface AppComponent : AndroidInjector<DaggerApplication>{

    fun inject(app : SwivelNewsApplication){}

    override fun inject(instance: DaggerApplication?)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application : Application) : Builder
        fun build() : AppComponent
    }
}
