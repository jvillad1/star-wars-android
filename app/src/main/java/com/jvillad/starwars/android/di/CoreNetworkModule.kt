package com.jvillad.starwars.android.di

import com.jvillad.starwars.android.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * [Module] to provide core network level dependencies.
 *
 * @author juan.villada
 */
@Module
@InstallIn(ApplicationComponent::class)
object CoreNetworkModule {

    @Provides
    internal fun providesLoggingInterceptor(): HttpLoggingInterceptor? =
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        } else null

    @Provides
    internal fun providesOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor?
    ): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .apply {
                loggingInterceptor?.also { addInterceptor(it) }
            }
}
