package com.jvillad.starwars.android.presentation.search.state

/**
 * Class for wrapping the current graph navigation state.
 *
 * @author juan.villada
 */
sealed class SearchNavigationState {
    object SearchFragment : SearchNavigationState()
    object CharacterDetailsFragment : SearchNavigationState()
}
