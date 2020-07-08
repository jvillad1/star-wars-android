package com.jvillad.starwars.android.commons.data

/**
 * Base Mapper interface for data mapping between project layers.
 *
 * @author juan.villada
 */
interface BaseMapper<in A, out B> {

    fun map(type: A): B
}
