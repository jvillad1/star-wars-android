package com.jvillad.starwars.android.commons.presentation.state

/**
 * Interface for navigation between views.
 *
 * @author juan.villada
 */
interface NavigationProvider<in A> {

    fun navigateTo(origin: A, destination: A)
}
