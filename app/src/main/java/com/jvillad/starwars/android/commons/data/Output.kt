package com.jvillad.starwars.android.commons.data

/**
 * A generic class that holds a value with an output status.
 * @param <T>
 *
 * @author juan.villada
 */
sealed class Output<out T> {
    data class Success<out T>(val data: T) : Output<T>()
    data class Error<out T>(val message: String) : Output<T>()

    companion object {
        fun <T> success(data: T): Output<T> =
            Success(data)

        fun <T> error(error_msg: String): Output<T> =
            Error(error_msg)
    }
}
