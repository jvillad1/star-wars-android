package com.jvillad.starwars.android.di

import com.jvillad.starwars.android.commons.settings.RemoteSettings.API_BASE_URL
import com.jvillad.starwars.android.data.remote.CharactersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * [Module] to provide Star Wars Characters network level dependencies.
 *
 * @author juan.villada
 */
@Module(includes = [CoreNetworkModule::class])
@InstallIn(ApplicationComponent::class)
object CharactersNetworkModule {

    @Provides
    internal fun providesOkHttpClient(
        builder: OkHttpClient.Builder
    ): OkHttpClient = with(builder) {
        build()
    }

    @Provides
    @Singleton
    internal fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    internal fun providesApi(retrofit: Retrofit): CharactersApi =
        retrofit.create(CharactersApi::class.java)
}
