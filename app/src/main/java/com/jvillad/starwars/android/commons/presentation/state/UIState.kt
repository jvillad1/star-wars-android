package com.jvillad.starwars.android.commons.presentation.state

import androidx.annotation.StringRes

/**
 * A generic class that holds UI common states.
 *
 * @author juan.villada
 */
sealed class UIState<out T : Any> {
    data class Loading(@StringRes val message: Int? = null) : UIState<Nothing>()
    data class Data<T : Any>(val data: T) : UIState<T>()
    data class Error(@StringRes val message: Int) : UIState<Nothing>()
}
