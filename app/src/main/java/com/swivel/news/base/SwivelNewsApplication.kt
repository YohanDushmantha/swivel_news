package com.swivel.news.base

import com.swivel.news.BuildConfig
import com.swivel.news.base.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber


/**
 * @author Yohan Dushmantha
 * @class SwivelNewsApplication
 *
 * Entry point of the Application
 */
class SwivelNewsApplication : DaggerApplication(){

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - START
     *----------------------------------------------------------------------------------------------*/

    override fun onCreate() {
        super.onCreate()

        plantTimber()
    }

    /**---------------------------------------------------------------------------------------------*
     * LIFECYCLE METHODS - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * INIT - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * applicationInjector to inject dagger dependencies for the application
     * @return DaggerAppComponent
     */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
        return appComponent
    }

    /**---------------------------------------------------------------------------------------------*
     * INIT - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * LOGGING - START
     *----------------------------------------------------------------------------------------------*/

    /**
     * plant timber for logging
     */
    private fun plantTimber(){
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    /**---------------------------------------------------------------------------------------------*
     * LOGGING - END
     *----------------------------------------------------------------------------------------------*/

    /**---------------------------------------------------------------------------------------------*
     * ENABLE MULTIDEX SUPPORT - START
     *----------------------------------------------------------------------------------------------*/

    /*override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }*/

    /**---------------------------------------------------------------------------------------------*
     * ENABLE MULTIDEX SUPPORT - END
     *----------------------------------------------------------------------------------------------*/



}
