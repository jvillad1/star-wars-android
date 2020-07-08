package com.jvillad.starwars.android.data.mapper

import com.jvillad.starwars.android.commons.data.BaseMapper
import com.jvillad.starwars.android.data.remote.model.CharacterResponse
import com.jvillad.starwars.android.data.remote.model.CharacterSearchResponse
import com.jvillad.starwars.android.data.remote.model.PlanetResponse
import com.jvillad.starwars.android.domain.search.model.Character
import com.jvillad.starwars.android.domain.search.model.Planet

/**
 * Mapper functions for mapping between data & domain layers.
 *
 * @author juan.villada
 */
object CharacterRemoteToDomain : BaseMapper<CharacterResponse, Character> {
    override fun map(type: CharacterResponse): Character {
        return Character(
            name = type.name,
            birthYear = type.birthYear,
            gender = type.gender
        )
    }
}

object PlanetRemoteToDomain : BaseMapper<PlanetResponse, Planet> {
    override fun map(type: PlanetResponse): Planet {
        return Planet(
            name = type.name,
            population = type.population
        )
    }
}
