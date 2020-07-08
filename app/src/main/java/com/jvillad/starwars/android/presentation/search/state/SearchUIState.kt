package com.jvillad.starwars.android.presentation.search.state

import com.jvillad.starwars.android.presentation.search.model.CharacterUI

/**
 * Class for wrapping the current view state.
 *
 * @author juan.villada
 */
sealed class SearchUIState {
    data class SearchLoadedState(val characters: List<CharacterUI>) : SearchUIState()
    object SearchClosedState : SearchUIState()
}
