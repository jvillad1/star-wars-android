package com.jvillad.starwars.android.data

import com.jvillad.starwars.android.commons.data.Output
import com.jvillad.starwars.android.data.remote.SearchRemoteDataSource
import com.jvillad.starwars.android.domain.search.model.Character
import com.jvillad.starwars.android.domain.search.repository.SearchRepository
import com.jvillad.starwars.android.presentation.search.model.CharacterUI
import javax.inject.Inject

/**
 * Search related Repository implementation.
 *
 * @author juan.villada
 */
class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {

    override suspend fun searchCharacters(name: String): Output<List<Character>> = searchRemoteDataSource.searchCharacters(name)

    override suspend fun getCharacterDetails(characterId: Int): Output<CharacterUI> {
        TODO("Not yet implemented")
    }
}
