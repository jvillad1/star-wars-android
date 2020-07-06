package com.jvillad.starwars.android.domain.search.usecase

import com.jvillad.starwars.android.commons.data.Output
import com.jvillad.starwars.android.domain.search.repository.SearchRepository
import com.jvillad.starwars.android.presentation.search.model.CharacterUI
import javax.inject.Inject

/**
 * UseCase for the [CharacterUI] search.
 *
 * @author juan.villada
 */
class SearchCharacters @Inject constructor(private val searchRepository: SearchRepository) {

    suspend operator fun invoke(query: String): Output<List<CharacterUI>> = searchRepository.searchCharacters(query)
}
