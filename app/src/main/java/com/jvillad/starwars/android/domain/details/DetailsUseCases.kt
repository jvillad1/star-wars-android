package com.jvillad.starwars.android.domain.details

import javax.inject.Inject

/**
 * Use Cases for the Details flow.
 *
 * @author juan.villada
 */
data class DetailsUseCases @Inject constructor(
    val getCharacterDetails: GetCharacterDetails
)
