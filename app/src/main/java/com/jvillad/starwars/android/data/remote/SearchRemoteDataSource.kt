package com.jvillad.starwars.android.data.remote

import com.jvillad.starwars.android.commons.data.Output
import com.jvillad.starwars.android.data.mapper.CharacterRemoteToDomain
import com.jvillad.starwars.android.data.mapper.PlanetRemoteToDomain
import com.jvillad.starwars.android.domain.search.model.Character
import javax.inject.Inject

/**
 * Search related remote DataSource.
 *
 * @author juan.villada
 */
class SearchRemoteDataSource @Inject constructor(private val charactersApi: CharactersApi) {

    suspend fun searchCharacters(name: String): Output<List<Character>> = try {
        val charactersList = charactersApi.searchCharacters(name).results.map {
            CharacterRemoteToDomain.map(it)
        }

        Output.success(charactersList)
    } catch (e: Throwable) {
        Output.error("Error retrieving the Characters Search from remote: ${e.message}")
    }
}
