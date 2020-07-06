package com.jvillad.starwars.android.di

import dagger.Module
import dagger.hilt.InstallIn
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
@InstallIn(ApplicationComponent::class)
abstract class DataModule
