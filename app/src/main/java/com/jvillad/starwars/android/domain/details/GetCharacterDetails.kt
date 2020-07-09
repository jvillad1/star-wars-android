package com.jvillad.starwars.android.domain.details

import com.jvillad.starwars.android.commons.data.Output
import com.jvillad.starwars.android.domain.search.model.Character
import com.jvillad.starwars.android.domain.search.repository.SearchRepository
import com.jvillad.starwars.android.presentation.search.model.CharacterUI
import javax.inject.Inject

/**
 * UseCase for the [CharacterUI] details.
 *
 * @author juan.villada
 */
class GetCharacterDetails @Inject constructor(private val searchRepository: SearchRepository) {

    suspend operator fun invoke(name: String): Output<List<Character>> = Output.success(listOf())
}
