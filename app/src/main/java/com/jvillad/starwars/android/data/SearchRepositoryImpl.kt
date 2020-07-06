package com.jvillad.starwars.android.data

import com.jvillad.starwars.android.commons.data.Output
import com.jvillad.starwars.android.domain.search.repository.SearchRepository
import com.jvillad.starwars.android.presentation.search.model.CharacterUI
import javax.inject.Inject

/**
 * Search related Repository implementation.
 *
 * @author juan.villada
 */
class SearchRepositoryImpl @Inject constructor() : SearchRepository {

    override suspend fun searchCharacters(query: String): Output<List<CharacterUI>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacterDetails(characterId: Int): Output<CharacterUI> {
        TODO("Not yet implemented")
    }
}
