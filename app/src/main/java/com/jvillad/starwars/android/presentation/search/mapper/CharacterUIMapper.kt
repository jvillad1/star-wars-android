package com.jvillad.starwars.android.presentation.search.mapper

import com.jvillad.starwars.android.commons.data.BaseMapper
import com.jvillad.starwars.android.domain.search.model.Character
import com.jvillad.starwars.android.domain.search.model.Planet
import com.jvillad.starwars.android.presentation.search.model.CharacterUI
import com.jvillad.starwars.android.presentation.search.model.PlanetUI

/**
 * Mapper functions for mapping between domain & UI layers.
 *
 * @author juan.villada
 */
object CharacterDomainToUI : BaseMapper<List<Character>, List<CharacterUI>> {
    override fun map(type: List<Character>): List<CharacterUI> {
        return type.map {
            CharacterUI(
                name = it.name,
                birthYear = it.birthYear,
                gender = it.gender,
                url = it.url
            )
        }
    }
}

object PlanetDomainToUI : BaseMapper<Planet, PlanetUI> {
    override fun map(type: Planet): PlanetUI {
        return PlanetUI(
            name = type.name,
            population = type.population
        )
    }
}
