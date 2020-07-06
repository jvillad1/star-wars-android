package com.jvillad.starwars.android.domain.search.usecase

import javax.inject.Inject

/**
 * Use Cases for the Search flow.
 *
 * @author juan.villada
 */
data class SearchUseCases @Inject constructor(
    val searchCharacters: SearchCharacters,
    val getCharacterDetails: SearchCharacters
)
