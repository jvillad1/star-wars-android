package com.jvillad.starwars.android.domain.search.repository

import com.jvillad.starwars.android.commons.data.Output
import com.jvillad.starwars.android.domain.search.model.Character
import com.jvillad.starwars.android.presentation.search.model.CharacterUI


/**
 * Search related Repository.
 *
 * @author juan.villada
 */
interface SearchRepository {

    suspend fun searchCharacters(name: String): Output<List<Character>>

    suspend fun getCharacterDetails(characterId: Int): Output<CharacterUI>
}
