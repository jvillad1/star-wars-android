package com.jvillad.starwars.android.data.remote.model

import com.squareup.moshi.Json

/**
 * Data class for Planet data entity (network DTO).
 *
 * @author juan.villada
 */
data class PlanetResponse(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "rotation_period")
    val rotation_period: String,
    @field:Json(name = "orbital_period")
    val orbital_period: String,
    @field:Json(name = "diameter")
    val diameter: String,
    @field:Json(name = "climate")
    val climate: String,
    @field:Json(name = "gravity")
    val gravity: String,
    @field:Json(name = "terrain")
    val terrain: String,
    @field:Json(name = "surface_water")
    val surface_water: String,
    @field:Json(name = "population")
    val population: String,
    @field:Json(name = "residents")
    val residents: List<String>,
    @field:Json(name = "films")
    val films: List<String>,
    @field:Json(name = "created")
    val created: String,
    @field:Json(name = "edited")
    val edited: String,
    @field:Json(name = "url")
    val url: String
)
