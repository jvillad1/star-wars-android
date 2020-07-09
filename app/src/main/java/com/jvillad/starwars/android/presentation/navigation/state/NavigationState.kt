package com.jvillad.starwars.android.presentation.navigation.state

import com.jvillad.starwars.android.presentation.search.model.CharacterUI

/**
 * Class for wrapping the navigation state.
 *
 * @author juan.villada
 */
sealed class NavigationState

/**
 * Search feature navigation state.
 */
sealed class SearchNavigationState : NavigationState() {
    object SearchFragment : SearchNavigationState()
}

/**
 * Details feature navigation state.
 */
sealed class DetailsNavigationState : NavigationState() {
    data class CharacterDetailsFragment(val characterUI: CharacterUI) : DetailsNavigationState()
}
