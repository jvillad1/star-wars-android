package com.jvillad.starwars.android.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * [Module] to provide cache/local level dependencies.
 *
 * @author juan.villada
 */
@Module
@InstallIn(ApplicationComponent::class)
object CacheModule
