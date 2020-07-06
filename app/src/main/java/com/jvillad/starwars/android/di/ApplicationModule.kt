package com.jvillad.starwars.android.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jvillad.starwars.android.commons.settings.PreferenceSettings.PREFS_FILE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * [Module] to provide Application level dependencies.
 *
 * @author juan.villada
 */
@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE)
}
