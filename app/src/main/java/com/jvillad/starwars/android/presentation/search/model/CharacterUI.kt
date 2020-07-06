package com.jvillad.starwars.android.presentation.search.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Data class for Character UI model.
 *
 * @author juan.villada
 */
@Parcelize
data class CharacterUI(
    val name: String,
    val birthYear: String,
    val height: String,
    val speciesName: String,
    val speciesLanguage: String,
    val speciesHomeworld: String,
    val planetsPopulation: String,
    val films: List<String>
) : Parcelable
