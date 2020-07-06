package com.jvillad.starwars.android.data.remote.model

import com.squareup.moshi.Json

/**
 * Data class for Character data entity (network DTO).
 *
 * @author juan.villada
 */
data class CharacterResponse(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "height")
    val height: String,
    @field:Json(name = "mass")
    val mass: String,
    @field:Json(name = "hair_color")
    val hairColor: String,
    @field:Json(name = "skin_color")
    val skinColor: String,
    @field:Json(name = "eye_color")
    val eyeColor: String,
    @field:Json(name = "birth_year")
    val birthYear: String,
    @field:Json(name = "gender")
    val gender: String,
    @field:Json(name = "homeworld")
    val homeworld: String,
    @field:Json(name = "films")
    val films: List<String>,
    @field:Json(name = "species")
    val species: List<String>,
    @field:Json(name = "vehicles")
    val vehicles: List<String>,
    @field:Json(name = "starships")
    val starships: List<String>,
    @field:Json(name = "created")
    val created: String,
    @field:Json(name = "edited")
    val edited: String,
    @field:Json(name = "url")
    val url: String
)
