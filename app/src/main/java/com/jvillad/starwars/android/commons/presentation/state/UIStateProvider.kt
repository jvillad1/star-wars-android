package com.jvillad.starwars.android.commons.presentation.state

/**
 * Interface for UI state updates.
 *
 * @author juan.villada
 */
interface UIStateProvider<T> {

    fun updateUIState(newUIState: T)
}
