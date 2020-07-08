package com.jvillad.starwars.android.data.remote.model

import com.squareup.moshi.Json

/**
 * Data class for Character Search data entity (network DTO).
 *
 * @author juan.villada
 */
data class CharacterSearchResponse(
    @field:Json(name = "count")
    val count: Int,
    @field:Json(name = "next")
    val next: String,
    @field:Json(name = "previous")
    val previous: String,
    @field:Json(name = "category")
    val category: String,
    @field:Json(name = "results")
    val results: List<CharacterResponse>
)
