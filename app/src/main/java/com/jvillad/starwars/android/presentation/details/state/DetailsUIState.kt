package com.jvillad.starwars.android.presentation.details.state

/**
 * Class for wrapping the current view state.
 *
 * @author juan.villada
 */
sealed class DetailsUIState {
    object DetailsLoadedState : DetailsUIState()
}
