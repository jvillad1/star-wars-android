package com.jvillad.starwars.android.di

import com.jvillad.starwars.android.data.SearchRepositoryImpl
import com.jvillad.starwars.android.domain.search.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent

/**
 * [Module] to provide data level dependencies.
 *
 * @author juan.villada
 */
@Module(
    includes = [
        CacheModule::class,
        CharactersNetworkModule::class
    ]
)
@InstallIn(ActivityRetainedComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun providesSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}
