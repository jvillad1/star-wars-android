package com.jvillad.starwars.android.presentation.search.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Data class for SearchDataWrapper UI model.
 *
 * @author juan.villada
 */
@Parcelize
data class SearchDataWrapper(
    val characters: List<CharacterUI> = listOf(),
    val showSearchInfoCard: Boolean = false,
    val showEmptyCharactersSearch: Boolean = false
) : Parcelable
