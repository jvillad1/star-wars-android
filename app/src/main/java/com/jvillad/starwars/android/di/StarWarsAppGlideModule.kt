package com.jvillad.starwars.android.di

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.jvillad.starwars.android.BuildConfig

/**
 * [GlideModule] to provide Glide dependencies.
 *
 * @author juan.villada
 */
@GlideModule
class StarWarsAppGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        if (BuildConfig.DEBUG) {
            builder.setLogLevel(Log.VERBOSE)
        }
    }
}
