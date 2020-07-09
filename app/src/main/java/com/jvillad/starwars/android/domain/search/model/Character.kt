package com.jvillad.starwars.android.domain.search.model

/**
 * Data class for Character domain model.
 *
 * @author juan.villada
 */
data class Character(
    val name: String,
    val birthYear: String,
    val gender: String,
    val url: String
)
