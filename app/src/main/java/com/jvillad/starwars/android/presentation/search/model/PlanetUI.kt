package com.jvillad.starwars.android.presentation.search.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *  Data class for Planet UI model.
 *
 * @author juan.villada
 */
@Parcelize
data class PlanetUI(
    val name: String,
    val population: String
) : Parcelable
