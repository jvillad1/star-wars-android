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
    val gender: String,
    val url: String
) : Parcelable
