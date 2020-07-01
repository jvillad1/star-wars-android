package com.jvillad.starwars.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Project main Application class that inherits from [Application].
 *
 * @author juan.villada
 */
@HiltAndroidApp
class StarWarsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Debugging
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
