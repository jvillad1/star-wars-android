package com.jvillad.starwars.android.domain.search.repository

import com.jvillad.starwars.android.commons.data.Output
import com.jvillad.starwars.android.presentation.search.model.CharacterUI


/**
 * Search related Repository.
 *
 * @author juan.villada
 */
interface SearchRepository {

    suspend fun searchCharacters(query: String): Output<List<CharacterUI>>

    suspend fun getCharacterDetails(characterId: Int): Output<CharacterUI>
}
