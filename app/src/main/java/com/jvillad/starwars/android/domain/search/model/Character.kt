package com.jvillad.starwars.android.domain.search.model

/**
 * Data class for Character domain model.
 *
 * @author juan.villada
 */
data class Character(
    val name: String,
    val birthYear: String,
    val height: String,
    val speciesName: String,
    val speciesLanguage: String,
    val speciesHomeworld: String,
    val planetsPopulation: String,
    val films: List<String>
)
